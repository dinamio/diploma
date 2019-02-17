package com.university.contractors.controller;

import com.university.contractors.Application;
import com.university.contractors.config.Endpoints;
import com.university.contractors.controller.payload.SignUpUser;
import com.university.contractors.controller.payload.SignUpUserBuilder;
import com.university.contractors.model.User;
import com.university.contractors.model.UserBuilder;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;

import static io.restassured.RestAssured.given;
import static java.net.HttpURLConnection.HTTP_OK;
import static java.net.HttpURLConnection.HTTP_UNAUTHORIZED;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {Application.class}, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SingUpControllerTest {

    private static final String USERNAME_TO_REGISTER = "john";
    private static final String PASSWORD_TO_REGISTER = "strongPassword";

    @LocalServerPort
    int port;

    @Before
    public void setUp() {
        RestAssured.port = port;
    }

    @Test
    public void shouldSignUpUser() {
        final SignUpUser userToSignUp = getUserBuilder()
                .build();

        getPreparedGiven()
                .body(userToSignUp)
                .post(Endpoints.SIGN_UP)
                .then().statusCode(HTTP_OK);

        final User signedUpUser = UserBuilder.anUser()
                .username(USERNAME_TO_REGISTER)
                .passwordHash(PASSWORD_TO_REGISTER)
                .build();

        getPreparedGiven()
                .body(signedUpUser)
                .post(Endpoints.LOGIN)
                .then()
                .statusCode(HTTP_OK);
    }

    @Test
    public void shouldNotSignInUserWithInvalidConfirmationPassword() {
        final SignUpUser userToSignUp = getUserBuilder()
                .confirmationPassword(PASSWORD_TO_REGISTER + " mistake in confirmation password")
                .build();

        getPreparedGiven()
                .body(userToSignUp)
                .post(Endpoints.SIGN_UP)
                .then().statusCode(HTTP_UNAUTHORIZED);
    }

    private SignUpUserBuilder getUserBuilder() {
        return SignUpUserBuilder.aSignUpUser()
                .username(USERNAME_TO_REGISTER)
                .password(PASSWORD_TO_REGISTER)
                .confirmationPassword(PASSWORD_TO_REGISTER);
    }

    private RequestSpecification getPreparedGiven() {
        return given().contentType(ContentType.JSON);
    }
}
