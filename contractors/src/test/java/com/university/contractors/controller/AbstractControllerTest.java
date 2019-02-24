package com.university.contractors.controller;

import com.university.contractors.Application;
import com.university.contractors.config.Endpoints;
import com.university.contractors.config.SecurityConstants;
import com.university.contractors.controller.payload.LoginUser;
import com.university.contractors.controller.payload.LoginUserBuilder;
import com.university.contractors.controller.payload.SignUpUser;
import com.university.contractors.controller.payload.SignUpUserBuilder;
import com.university.contractors.model.IdEntity;
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
import static org.apache.commons.lang3.StringUtils.SPACE;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {Application.class}, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public abstract class AbstractControllerTest<I, E extends IdEntity<I>> {

    private final String JOHN_USERNAME = "john" + UUID.randomUUID();
    private final String JOHN_PASSWORD = "strongPassword";

    private final String ENDPOINT = getEndpoint();

    private final Class<E> entityType = getEntityType();

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
    public void shouldAllowGetForAuthenticatedUser() {
        final E entityToCreate = getArbitraryEntity();
        final Header header = getAuthorizationHeader(JOHN_USERNAME, JOHN_PASSWORD);
        final E entityInResponse = sendCreateEntityRequest(entityToCreate, header);

        assertEntities(entityToCreate, entityInResponse);

        final Response getResponse = getPreparedRequestSpecification(header)
                .get(getRequestPathToEntity(entityInResponse));
        final E savedEntity = getResponse.as(entityType);

        assertEntities(entityInResponse, savedEntity);
    }

    @Test
    public void shouldAllowPostForAuthenticatedUser() {
        final E entityToCreate = getArbitraryEntity();

        given().contentType(ContentType.JSON)
                .body(entityToCreate).post(ENDPOINT).then().statusCode(HTTP_UNAUTHORIZED);

        final Header header = getAuthorizationHeader(JOHN_USERNAME, JOHN_PASSWORD);
        getPreparedRequestSpecification(header)
                .body(entityToCreate)
                .post(ENDPOINT)
                .then()
                .statusCode(HTTP_OK);
    }

    @Test
    public void shouldAllowPutForAuthenticatedUser() {
        final Header header = getAuthorizationHeader(JOHN_USERNAME, JOHN_PASSWORD);
        final E entityToUpdate = sendCreateEntityRequest(getArbitraryEntity(), header);

        final E entityToUpdateWith = getArbitraryEntity();
        entityToUpdateWith.setId(entityToUpdate.getId());

        final Response putResponse = getPreparedRequestSpecification(header)
                .body(entityToUpdateWith)
                .put(getRequestPathToEntity(entityToUpdate));
        putResponse.then()
                .statusCode(HTTP_OK);

        final E updatedEntity = putResponse.getBody().as(entityType);
        assertEntities(entityToUpdateWith, updatedEntity);
    }

    @Test
    public void shouldAllowDeleteForAuthenticatedUsers() {
        final Header header = getAuthorizationHeader(JOHN_USERNAME, JOHN_PASSWORD);
        final E entityToDelete = sendCreateEntityRequest(getArbitraryEntity(), header);

        getPreparedRequestSpecification(header)
                .delete(getRequestPathToEntity(entityToDelete))
                .then()
                .statusCode(HTTP_OK);

        getPreparedRequestSpecification(header)
                .get(getRequestPathToEntity(entityToDelete))
                .then().statusCode(HTTP_NOT_FOUND);
    }

    @Test
    public void shouldResponseWith404StatusCodeWhenEntityToDeleteDoesNotExists() {
        final Header header = getAuthorizationHeader(JOHN_USERNAME, JOHN_PASSWORD);
        final E entityToDelete = sendCreateEntityRequest(getArbitraryEntity(), header);

        getPreparedRequestSpecification(header)
                .delete(getRequestPathToEntity(entityToDelete))
                .then()
                .statusCode(HTTP_OK);

        getPreparedRequestSpecification(header)
                .delete(getRequestPathToEntity(entityToDelete))
                .then().statusCode(HTTP_NOT_FOUND);
    }

    @Test
    public void shouldResponseWith401StatusCodeForUnauthorizedUsers() {
        final Header header = getAuthorizationHeader(JOHN_USERNAME, JOHN_PASSWORD);
        final E entityToTest = sendCreateEntityRequest(getArbitraryEntity(), header);

        given().contentType(ContentType.JSON)
                .get(ENDPOINT)
                .then()
                .statusCode(HTTP_UNAUTHORIZED);
        given().contentType(ContentType.JSON)
                .body(entityToTest)
                .post(ENDPOINT)
                .then()
                .statusCode(HTTP_UNAUTHORIZED);

        final E entityToUpdateWith = getArbitraryEntity();
        entityToUpdateWith.setId(entityToTest.getId());

        given().contentType(ContentType.JSON)
                .body(entityToTest)
                .put(ENDPOINT)
                .then()
                .statusCode(HTTP_UNAUTHORIZED);
        given().contentType(ContentType.JSON)
                .delete(getRequestPathToEntity(entityToTest))
                .then()
                .statusCode(HTTP_UNAUTHORIZED);
    }

    private E sendCreateEntityRequest(E entityToCreate, Header header) {
        final Response postResponse = getPreparedRequestSpecification(header)
                .body(entityToCreate)
                .post(ENDPOINT);
        postResponse.then().statusCode(HTTP_OK);
        return postResponse.as(entityType);
    }

    private Header getAuthorizationHeader(String username, String password) {
        final String token = requestToken(JOHN_USERNAME, JOHN_PASSWORD);
        return getAuthorizationHeader(token);
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

    private String getRequestPathToEntity(E entityToDelete) {
        return ENDPOINT + "/" + entityToDelete.getId();
    }

    private Header getAuthorizationHeader(String token) {
        return new Header(SecurityConstants.HEADER_NAME, SecurityConstants.TOKEN_PREFIX + SPACE + token);
    }

    abstract E getArbitraryEntity();

    abstract void assertEntities(E expected, E actual);

    protected abstract String getEndpoint();

    protected abstract Class<E> getEntityType();
}
