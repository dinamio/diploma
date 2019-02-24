package com.university.contractors.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;

import static com.university.contractors.config.SecurityConstants.DEFAULT_EXPIRATION_TIME;

@Service
public class AuthenticationService {
    private final TokenBuilder tokenBuilder;
    private final UserService userService;

    @Autowired
    public AuthenticationService(TokenBuilder tokenBuilder, UserService userService) {
        this.tokenBuilder = tokenBuilder;
        this.userService = userService;
        ;
    }

    public String processSuccessfulAuthentication(String username) {
        final ZonedDateTime tokenExpirationTime = ZonedDateTime.now(ZoneOffset.UTC).plus(DEFAULT_EXPIRATION_TIME, ChronoUnit.MILLIS);
        final String token = tokenBuilder.buildToken(username, tokenExpirationTime);
        userService.saveUserToken(username, token);
        return token;
    }
}
