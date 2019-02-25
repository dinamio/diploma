package com.university.contractors.controller;

import com.university.contractors.config.Endpoints;
import com.university.contractors.controller.payload.SignUpUser;
import com.university.contractors.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SingUpController {

    private final UserService userService;

    @Autowired
    public SingUpController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(path = Endpoints.SIGN_UP)
    public void singUpUser(@RequestBody SignUpUser userToSignUp) {
        userService.signUpUser(userToSignUp);
    }

}
