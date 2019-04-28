package com.university.contractors.service;

public class EntityParseException extends RuntimeException {

    EntityParseException() {
        super("An exception occurred during parsing of given JSON.");
    }
}
