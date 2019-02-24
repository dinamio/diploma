package com.university.contractors.service;

import com.university.contractors.config.SecurityConstants;

public class AuthorizationInvalidPrefixException extends AbstractAuthorizationException {

    public AuthorizationInvalidPrefixException() {
        super(String.format("Authorization header value should start with '%s' prefix.",
                SecurityConstants.TOKEN_PREFIX));
    }
}
