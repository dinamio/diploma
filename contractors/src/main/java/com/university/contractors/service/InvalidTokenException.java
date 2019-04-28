package com.university.contractors.service;

public class InvalidTokenException extends RuntimeException {

    InvalidTokenException() {
        super("Given token doesn't match one on the server.");
    }
}
