package com.university.contractors.config;

import java.util.concurrent.TimeUnit;

public interface SecurityConstants {

    String SECRET = "secret";
    String TOKEN_PREFIX = "Bearer";
    String HEADER_NAME = "Authorization";

    long DEFAULT_EXPIRATION_TIME = TimeUnit.DAYS.toMillis(1);
}
