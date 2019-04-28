package com.university.contractors.controller;

import com.university.contractors.config.Endpoints;
import com.university.contractors.config.TestBase;
import com.university.contractors.controller.payload.LoginUser;
import com.university.contractors.controller.payload.LoginUserBuilder;
import com.university.contractors.controller.payload.SignUpUser;
import com.university.contractors.controller.payload.SignUpUserBuilder;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.junit.Before;
import org.junit.Test;
import org.springframework.boot.web.server.LocalServerPort;

import java.util.UUID;

import static io.restassured.RestAssured.given;
import static java.net.HttpURLConnection.HTTP_OK;
import static java.net.HttpURLConnection.HTTP_UNAUTHORIZED;

public class SingUpControllerTest extends TestBase {

    private static final String PREFIX_USERNAME_TO_REGISTER = "john";
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

        final LoginUser signedUpUser = LoginUserBuilder.aLoginUser()
                .username(userToSignUp.getUsername())
                .password(userToSignUp.getPassword())
                .build();

        getPreparedGiven()
                .body(signedUpUser)
                .post(Endpoints.LOGIN)
                .then()
                .statusCode(HTTP_OK);
    }

    @Test
    public void shouldNotSignUpUserWithInvalidConfirmationPassword() {
        final SignUpUser userToSignUp = getUserBuilder()
                .confirmationPassword(PASSWORD_TO_REGISTER + " mistake in confirmation password")
                .build();

        getPreparedGiven()
                .body(userToSignUp)
                .post(Endpoints.SIGN_UP)
                .then().statusCode(HTTP_UNAUTHORIZED);
    }

    @Test
    public void shouldNotSignUpUserWithWithDuplicatedName() {
        final SignUpUser userToSignUp = getUserBuilder()
                .build();

        getPreparedGiven()
                .body(userToSignUp)
                .post(Endpoints.SIGN_UP)
                .then().statusCode(HTTP_OK);

        final String anOtherPassword = "anOtherPassword";
        getPreparedGiven().body(getUserBuilder()
                .username(userToSignUp.getUsername())
                .password(anOtherPassword)
                .confirmationPassword(anOtherPassword)
                .build())
                .post(Endpoints.SIGN_UP)
                .then()
                .statusCode(HTTP_UNAUTHORIZED);

        final LoginUser signedUpUser = LoginUserBuilder.aLoginUser()
                .username(userToSignUp.getUsername())
                .password(userToSignUp.getPassword())
                .build();

        getPreparedGiven()
                .body(signedUpUser)
                .post(Endpoints.LOGIN)
                .then()
                .statusCode(HTTP_OK);
    }

    private SignUpUserBuilder getUserBuilder() {
        return SignUpUserBuilder.aSignUpUser()
                .username(PREFIX_USERNAME_TO_REGISTER + UUID.randomUUID().toString())
                .password(PASSWORD_TO_REGISTER)
                .confirmationPassword(PASSWORD_TO_REGISTER);
    }

    private RequestSpecification getPreparedGiven() {
        return given().contentType(ContentType.JSON);
    }
}
