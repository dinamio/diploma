package com.university.contractors.service;

public class UsernameAlreadyRegisteredException extends RuntimeException {

    public UsernameAlreadyRegisteredException() {
        super("User with given username was already registered.");
    }
}
