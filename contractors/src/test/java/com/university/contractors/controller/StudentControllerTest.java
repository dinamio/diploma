package com.university.contractors.controller;

import com.university.contractors.Application;
import com.university.contractors.config.Endpoints;
import com.university.contractors.config.SecurityConstants;
import com.university.contractors.controller.payload.SignUpUser;
import com.university.contractors.controller.payload.SignUpUserBuilder;
import com.university.contractors.model.Student;
import com.university.contractors.model.StudentBuilder;
import com.university.contractors.model.User;
import com.university.contractors.model.UserBuilder;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.http.Header;
import io.restassured.response.Response;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.UUID;

import static io.restassured.RestAssured.given;
import static java.net.HttpURLConnection.HTTP_OK;
import static java.net.HttpURLConnection.HTTP_UNAUTHORIZED;
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
                .then().statusCode(HTTP_OK);
    }

    @Test
    public void shouldAuthorizeStudentsCreationForAuthenticatedUser() {
        final Student studentToCreate = StudentBuilder.aStudent().name("John")
                .surname("Smith")
                .middleName("Mihailovich")
                .build();

        given().contentType(ContentType.JSON)
                .body(studentToCreate).post(Endpoints.STUDENTS).then().statusCode(HTTP_UNAUTHORIZED);

        final String token = requestToken(JOHN_USERNAME, JOHN_PASSWORD);
        final Header header = getHeader(token);

        given().header(header)
                .body(studentToCreate)
                .contentType(ContentType.JSON)
                .post(Endpoints.STUDENTS)
                .then()
                .statusCode(HTTP_OK);
    }

    @Test
    public void shouldAuthorizeStudentsFetchingForAuthenticatedUser() {
        final Student studentToCreate = StudentBuilder.aStudent().name("John")
                .surname("Smith")
                .middleName("Mihailovich")
                .build();

        final String token = requestToken(JOHN_USERNAME, JOHN_PASSWORD);
        final Header header = getHeader(token);

        final Response postResponse = given().header(header)
                .body(studentToCreate)
                .contentType(ContentType.JSON)
                .post(Endpoints.STUDENTS);
        postResponse.then().statusCode(HTTP_OK);
        final Student studentInResponse = postResponse.as(Student.class);

        assertEquals(studentToCreate.getSurname(), studentInResponse.getSurname()); // todo change to full field comparing except id

        final Response getResponse = given().header(header)
                .get(Endpoints.STUDENTS + "/" + studentInResponse.getId());
        final Student savedStudent = getResponse.as(Student.class);

        assertEquals(studentInResponse.getSurname(), savedStudent.getSurname()); // todo change to full field comparing except id
    }

    private String requestToken(String username, String password) {
        final User userToRequestTokenOf = UserBuilder.anUser().username(username)
                .passwordHash(password)
                .build();

        final Response response = given().body(userToRequestTokenOf).post(Endpoints.LOGIN);
        final String token = response.getBody().asString();
        response.then().statusCode(HTTP_OK);
        return token;
    }

    private Header getHeader(String token) {
        return new Header(SecurityConstants.HEADER_NAME, SecurityConstants.TOKEN_PREFIX + token);
    }
}