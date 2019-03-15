package com.sample.oauth2.auth_server.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
public class UserController {

    @RequestMapping(value = {"/user/me"}, produces = "application/json")
    public Principal user(Principal principal) {
        System.out.println("Hello");
        System.out.println(principal);
        return principal;
    }
}
