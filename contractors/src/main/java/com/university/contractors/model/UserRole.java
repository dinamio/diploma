package com.university.contractors.model;

public enum UserRole {

    ADMIN("ADMIN"), USER("USER");

    private final String value;

    UserRole(String userRoleValue) {
        value = userRoleValue;
    }

    public String getValue() {
        return value;
    }
}
