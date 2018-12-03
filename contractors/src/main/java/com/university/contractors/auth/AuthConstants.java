package com.university.contractors.auth;


class AuthConstants {

    // todo move to properties
    static final long ACCESS_TOKEN_VALIDITY_SECONDS = 5*60*60;
    static final String SIGNING_KEY = "secret";
    static final String TOKEN_PREFIX = "Bearer ";
    static final String HEADER_STRING = "Authorization";

    private AuthConstants() {}
}
