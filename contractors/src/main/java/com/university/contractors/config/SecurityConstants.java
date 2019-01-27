package com.university.contractors.config;

public interface SecurityConstants {

    String SECRET = "secret";
    String TOKEN_PREFIX = "Bearer ";
    String HEADER_NAME = "Authorization";
    // todo: remove magic number.
    long EXPIRATION_TIME = 864_000_000L; // one day

}
