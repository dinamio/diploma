package com.university.contractors;

import com.university.contractors.config.Endpoints;
import com.university.contractors.model.User;
import com.university.contractors.model.UserBuilder;
import io.restassured.RestAssured;
import io.restassured.response.Response;
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
public class AuthenticationTest {

    private static final String DEFAULT_ADMIN_USERNAME = "admin";
    private static final String DEFAULT_ADMIN_PASSWORD = "pass";

    @LocalServerPort
    int port;

    @Before
    public void setUp() {
        RestAssured.port = port;
    }

    @Test
    public void shouldAuthenticateUsersWithValidCredentials() {
        final User user = UserBuilder.anUser()
                .username(DEFAULT_ADMIN_USERNAME)
                .password(DEFAULT_ADMIN_PASSWORD)
                .build();

        final Response response = given().body(user).post(Endpoints.LOGIN);

        response.then().statusCode(HTTP_OK);
    }

    @Test
    public void shouldUnAuthorizeUsersWithInvalidUsername() {
        final String invalidUsername = DEFAULT_ADMIN_USERNAME + "invalid";

        final User user = UserBuilder.anUser()
                .username(invalidUsername)
                .password(DEFAULT_ADMIN_PASSWORD)
                .build();

        final Response response = given().body(user).post(Endpoints.LOGIN);

        response.then().statusCode(HTTP_UNAUTHORIZED);
    }

    @Test
    public void shouldUnAuthorizeUsersWithInvalidPassword() {
        final String invalidPassword = DEFAULT_ADMIN_PASSWORD + "invalid";

        final User user = UserBuilder.anUser()
                .username(DEFAULT_ADMIN_USERNAME)
                .password(invalidPassword)
                .build();

        final Response response = given().body(user).post(Endpoints.LOGIN);

        response.then().statusCode(HTTP_UNAUTHORIZED);
    }
}
