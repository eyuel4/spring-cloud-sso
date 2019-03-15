package com.sample.oauth2.invite_entry.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

/**
 * Because this application is also a User Info Resource Server, we expose info about the logged in user at:
 *
 *     http://localhost:9090/auth/user
 */
@RestController
public class MyResourceController {

    @RequestMapping("/user/me")
    public Principal user(Principal user) {
      return user;
    }

}
