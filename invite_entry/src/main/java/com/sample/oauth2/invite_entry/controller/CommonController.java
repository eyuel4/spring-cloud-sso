package com.sample.oauth2.invite_entry.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
public class CommonController {

    @RequestMapping(value = "/properties", method = RequestMethod.GET)
    @ResponseBody
    @PreAuthorize("hasAnyRole('ADMIN')")
    public String getApplicationProperties() {
        return "Hello World";
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public void logout() {

    }

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    @ResponseBody
    public String getTest() {
        System.out.println("Reached end of this line");
        return "This is test with no authentication";
    }

    @RequestMapping("/user")
    public Principal user(Principal user) {
        return user;
    }

    @GetMapping("/login")
    @ResponseBody
    public String login() {
        return "Am here";
    }
}
