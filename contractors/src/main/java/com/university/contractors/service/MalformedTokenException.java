package com.university.contractors.service;

import com.university.contractors.config.SecurityConstants;

public class MalformedTokenException extends RuntimeException {

    public MalformedTokenException() {
        super(String.format("Token should start with '%s' prefix and contain authorization token, " +
                "separated from the prefix with single space.", SecurityConstants.TOKEN_PREFIX));
    }
}
