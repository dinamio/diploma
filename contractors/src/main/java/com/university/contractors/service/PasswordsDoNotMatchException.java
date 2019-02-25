package com.university.contractors.service;

public class PasswordsDoNotMatchException extends RuntimeException {

    public PasswordsDoNotMatchException() {
        super("Password and confirmation password do not match.");
    }
}
