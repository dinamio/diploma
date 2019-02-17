package com.university.contractors.controller;

import com.university.contractors.config.Endpoints;
import com.university.contractors.controller.payload.SignUpUser;
import com.university.contractors.service.SignUpUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SingUpController {

    private final SignUpUserService signUpUserService;

    @Autowired
    public SingUpController(SignUpUserService signUpUserService) {
        this.signUpUserService = signUpUserService;
    }

    @PostMapping(path = Endpoints.SIGN_UP)
    public void singUpUser(@RequestBody SignUpUser userToSignUp) {
        signUpUserService.signUpUser(userToSignUp);
    }

}
