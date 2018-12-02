package com.university.contractors.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Base64;

import static java.nio.charset.StandardCharsets.UTF_8;

@Component
public class Base64PasswordEncoder implements PasswordEncoder {

    private static final Logger logger = LoggerFactory.getLogger(Base64PasswordEncoder.class);

    @Override
    public String encode(CharSequence rawPassword) {
        final byte[] encodedBytes = Base64.getEncoder().encode(rawPassword.toString().getBytes(UTF_8));
        final String encodedPassword = new String(encodedBytes);

        logger.info("Password '{}' was encoded to '{}'", rawPassword, encodedPassword);

        return encodedPassword;
    }

    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        final String encodedInputPassword = encode(rawPassword);

        logger.debug("Got password to match: '{}'", rawPassword);
        logger.debug("Got password: '{}', stored password: '{}'", encodedInputPassword, encodedPassword);

        return encodedInputPassword.equals(encodedPassword);
    }
}
