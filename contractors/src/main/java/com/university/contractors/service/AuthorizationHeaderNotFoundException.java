package com.university.contractors.service;

import com.university.contractors.config.SecurityConstants;

public class AuthorizationHeaderNotFoundException extends RuntimeException {

    public AuthorizationHeaderNotFoundException() {
        super(String.format("Request must contain '%s' header.", SecurityConstants.HEADER_NAME));
    }
}
