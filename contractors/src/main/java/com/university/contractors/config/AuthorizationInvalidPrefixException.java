package com.university.contractors.config;

public class AuthorizationInvalidPrefixException extends AbstractAuthorizationException {

    public AuthorizationInvalidPrefixException() {
        super(String.format("Authorization header value should start with '%s' prefix.",
                SecurityConstants.TOKEN_PREFIX));
    }
}
