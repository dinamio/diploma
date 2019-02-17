package com.university.contractors;

import com.university.contractors.config.Endpoints;
import com.university.contractors.controller.payload.LoginUser;
import com.university.contractors.controller.payload.LoginUserBuilder;
import com.university.contractors.controller.payload.SignUpUser;
import com.university.contractors.controller.payload.SignUpUserBuilder;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
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

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {Application.class}, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AuthenticationTest {

    private final String USERNAME_TO_REGISTER = "john" + UUID.randomUUID();
    private final String PASSWORD_TO_REGISTER = "strongPassword";

    @LocalServerPort
    int port;

    @Before
    public void setUp() {
        RestAssured.port = port;

        final SignUpUser userJohn = SignUpUserBuilder.aSignUpUser()
                .username(USERNAME_TO_REGISTER)
                .password(PASSWORD_TO_REGISTER)
                .confirmationPassword(PASSWORD_TO_REGISTER)
                .build();

        given().body(userJohn)
                .contentType(ContentType.JSON)
                .post(Endpoints.SIGN_UP)
                .then().statusCode(HTTP_OK);
    }

    @Test
    public void shouldAuthenticateUsersWithValidCredentials() {
        final LoginUser user = LoginUserBuilder.aLoginUser()
                .username(USERNAME_TO_REGISTER)
                .password(PASSWORD_TO_REGISTER)
                .build();

        final Response response = given().body(user).post(Endpoints.LOGIN);

        response.then().statusCode(HTTP_OK);
    }

    @Test
    public void shouldUnAuthorizeUsersWithInvalidUsername() {
        final String invalidUsername = USERNAME_TO_REGISTER + "invalid";

        final LoginUser user = LoginUserBuilder.aLoginUser()
                .username(invalidUsername)
                .password(PASSWORD_TO_REGISTER)
                .build();

        final Response response = given().body(user).post(Endpoints.LOGIN);

        response.then().statusCode(HTTP_UNAUTHORIZED);
    }

    @Test
    public void shouldUnAuthorizeUsersWithInvalidPassword() {
        final String invalidPassword = PASSWORD_TO_REGISTER + "invalid";

        final LoginUser user = LoginUserBuilder.aLoginUser()
                .username(USERNAME_TO_REGISTER)
                .password(invalidPassword)
                .build();

        final Response response = given().body(user).post(Endpoints.LOGIN);

        response.then().statusCode(HTTP_UNAUTHORIZED);
    }
}
