package com.university.contractors.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping(path = "/hello")
    public String hello() {
        return "Hello world!";
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping(path = "/restricted")
    public String securedUrl() {
        return "This is restricted zone";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping(path = "/stuff_only")
    public String stuffOnyZoneUrl() {
        return "This zone is only for admin";
    }
}
