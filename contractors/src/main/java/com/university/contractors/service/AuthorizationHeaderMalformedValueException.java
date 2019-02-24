package com.university.contractors.service;

import com.university.contractors.config.SecurityConstants;

public class AuthorizationHeaderMalformedValueException extends AbstractAuthorizationException {

    public AuthorizationHeaderMalformedValueException(String authorizationHeaderValue) {
        super(String.format("Authorization header value - '%s' has malformed value. " +
                        "It should be '%s' token prefix and itself token, separated with space.",
                authorizationHeaderValue, SecurityConstants.TOKEN_PREFIX));
    }
}
