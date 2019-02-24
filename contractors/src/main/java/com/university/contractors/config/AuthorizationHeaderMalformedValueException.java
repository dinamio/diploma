package com.university.contractors.config;

public class AuthorizationHeaderMalformedValueException extends AbstractAuthorizationException {

    public AuthorizationHeaderMalformedValueException() {
        super(String.format("Authorization Header has malformed value. " +
                        "It should be '%s' token prefix and itself token, separated with space.",
                SecurityConstants.TOKEN_PREFIX));
    }
}
