package com.university.contractors.controller.payload;

public final class SignUpUserBuilder {
    private SignUpUser signUpUser;

    private SignUpUserBuilder() {
        signUpUser = new SignUpUser();
    }

    public static SignUpUserBuilder aSignUpUser() {
        return new SignUpUserBuilder();
    }

    public SignUpUserBuilder username(String username) {
        signUpUser.setUsername(username);
        return this;
    }

    public SignUpUserBuilder password(String password) {
        signUpUser.setPassword(password);
        return this;
    }

    public SignUpUserBuilder confirmationPassword(String confirmationPassword) {
        signUpUser.setConfirmationPassword(confirmationPassword);
        return this;
    }

    public SignUpUser build() {
        return signUpUser;
    }
}
