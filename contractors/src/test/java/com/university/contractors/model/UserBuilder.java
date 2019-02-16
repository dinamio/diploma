package com.university.contractors.model;

public final class UserBuilder {
    private User user;

    private UserBuilder() {
        user = new User();
    }

    public static UserBuilder anUser() {
        return new UserBuilder();
    }

    public UserBuilder username(String username) {
        user.setUsername(username);
        return this;
    }

    public UserBuilder password(String password) {
        user.setPassword(password);
        return this;
    }

    public User build() {
        return user;
    }
}
