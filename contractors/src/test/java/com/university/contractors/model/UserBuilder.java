package com.university.contractors.model;

public final class UserBuilder {
    private User user;

    private UserBuilder() {
        user = new User();
    }

    public static UserBuilder anUser() {
        return new UserBuilder();
    }

    public UserBuilder id(Long id) {
        user.setId(id);
        return this;
    }

    public UserBuilder username(String username) {
        user.setUsername(username);
        return this;
    }

    public UserBuilder passwordHash(String passwordHash) {
        user.setPasswordHash(passwordHash);
        return this;
    }

    public UserBuilder role(UserRole role) {
        user.setUserRole(role);
        return this;
    }

    public UserBuilder token(String token) {
        user.setToken(token);
        return this;
    }

    public User build() {
        return user;
    }
}
