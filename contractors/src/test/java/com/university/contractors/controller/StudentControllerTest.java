package com.university.contractors.controller;

import com.university.contractors.Application;
import com.university.contractors.config.Endpoints;
import com.university.contractors.config.SecurityConstants;
import com.university.contractors.controller.payload.LoginUser;
import com.university.contractors.controller.payload.LoginUserBuilder;
import com.university.contractors.controller.payload.SignUpUser;
import com.university.contractors.controller.payload.SignUpUserBuilder;
import com.university.contractors.model.Student;
import com.university.contractors.model.StudentBuilder;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.http.Header;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.UUID;

import static io.restassured.RestAssured.given;
import static java.net.HttpURLConnection.*;
import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {Application.class}, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class StudentControllerTest {

    private final String JOHN_USERNAME = "john" + UUID.randomUUID();
    private final String JOHN_PASSWORD = "strongPassword";

    @LocalServerPort
    int port;

    @Before
    public void setUp() {
        RestAssured.port = port;

        final SignUpUser userJohn = SignUpUserBuilder.aSignUpUser()
                .username(JOHN_USERNAME)
                .password(JOHN_PASSWORD)
                .confirmationPassword(JOHN_PASSWORD)
                .build();
        given().body(userJohn)
                .contentType(ContentType.JSON)
                .post(Endpoints.SIGN_UP)
                .then()
                .statusCode(HTTP_OK);
    }

    @Test
    public void shouldAllowStudentsCreationForAuthenticatedUser() {
        final Student studentToCreate = getStudentBuilder().build();

        given().contentType(ContentType.JSON)
                .body(studentToCreate).post(Endpoints.STUDENTS).then().statusCode(HTTP_UNAUTHORIZED);

        final Header header = getAuthorizationHeader(JOHN_USERNAME, JOHN_PASSWORD);

        getPreparedRequestSpecification(header)
                .body(studentToCreate)
                .post(Endpoints.STUDENTS)
                .then()
                .statusCode(HTTP_OK);
    }

    @Test
    public void shouldAllowStudentsFetchingForAuthenticatedUser() {
        final Student studentToCreate = getStudentBuilder().build();
        final Header header = getAuthorizationHeader(JOHN_USERNAME, JOHN_PASSWORD);
        final Student studentInResponse = sendCreateEntityRequest(studentToCreate, header);

        assertEntities(studentToCreate, studentInResponse);

        final Response getResponse = getPreparedRequestSpecification(header)
                .get(Endpoints.STUDENTS + "/" + studentInResponse.getId());
        final Student savedStudent = getResponse.as(Student.class);

        assertEntities(studentInResponse, savedStudent);
    }

    @Test
    public void shouldAllowStudentDeletionForAuthenticatedUsers() {
        final Header header = getAuthorizationHeader(JOHN_USERNAME, JOHN_PASSWORD);
        final Student studentToDelete = sendCreateEntityRequest(getStudentBuilder().build(), header);

        getPreparedRequestSpecification(header)
                .delete(Endpoints.STUDENTS + "/" + studentToDelete.getId())
                .then()
                .statusCode(HTTP_OK);

        getPreparedRequestSpecification(header)
                .get(Endpoints.STUDENTS + "/" + studentToDelete.getId())
                .then().statusCode(HTTP_NOT_FOUND);
    }

    @Test
    public void shouldResponseWithNotFoundStatusCodeIfUserToDeleteDoesNotExists() {
        final Header header = getAuthorizationHeader(JOHN_USERNAME, JOHN_PASSWORD);
        final Student studentToDelete = sendCreateEntityRequest(getStudentBuilder().build(), header);

        getPreparedRequestSpecification(header)
                .delete(Endpoints.STUDENTS + "/" + studentToDelete.getId())
                .then()
                .statusCode(HTTP_OK);

        getPreparedRequestSpecification(header)
                .delete(Endpoints.STUDENTS + "/" + studentToDelete.getId())
                .then().statusCode(HTTP_NOT_FOUND);
    }

    private Student sendCreateEntityRequest(Student studentToCreate, Header header) {
        final Response postResponse = getPreparedRequestSpecification(header)
                .body(studentToCreate)
                .post(Endpoints.STUDENTS);
        postResponse.then().statusCode(HTTP_OK);
        return postResponse.as(Student.class);
    }

    private Header getAuthorizationHeader(String username, String password) {
        final String token = requestToken(JOHN_USERNAME, JOHN_PASSWORD);
        return getHeader(token);
    }

    private RequestSpecification getPreparedRequestSpecification(Header header) {
        return given().header(header)
                .contentType(ContentType.JSON);
    }

    private String requestToken(String username, String password) {
        final LoginUser userToRequestTokenOf = LoginUserBuilder.aLoginUser()
                .username(username)
                .password(password)
                .build();

        final Response response = given().body(userToRequestTokenOf).post(Endpoints.LOGIN);
        final String token = response.getBody().asString();
        response.then().statusCode(HTTP_OK);
        return token;
    }

    private Header getHeader(String token) {
        return new Header(SecurityConstants.HEADER_NAME, SecurityConstants.TOKEN_PREFIX + token);
    }

    private StudentBuilder getStudentBuilder() {
        return StudentBuilder.aStudent().name("John" + UUID.randomUUID())
                .surname("Smith" + UUID.randomUUID())
                .middleName("Mihailovich" + UUID.randomUUID());
    }

    private void assertEntities(Student expected, Student actual) {
        assertEquals("Expected student surname doesn't match actual.",
                expected.getSurname(), actual.getSurname());
        assertEquals("Expected student name doesn't match actual.",
                expected.getName(), actual.getName());
        assertEquals("Expected student surname doesn't match actual.",
                expected.getMiddleName(), actual.getMiddleName());
        assertEquals("Expected student date of birth doesn't match actual.",
                expected.getDateOfBirth(), actual.getDateOfBirth());
        assertEquals("Expected student country of birth doesn't match actual.",
                expected.getCountry(), actual.getCountry());
    }
}