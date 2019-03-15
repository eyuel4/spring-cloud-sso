package com.sample.oauth2.invite_gateway.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestOperations;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class InviteController {
    @Autowired
    private RestOperations restOperations;

//    @GetMapping("/")
//    @ResponseBody
//    public String helloFromBaeldung() {
//        return "Hello From Baeldung!";
//    }

    @GetMapping("/properties")
    public String person() {
        System.out.println("I am here");
        String personResourceUrl = "http://localhost:6000/properties";
        return restOperations.getForObject(personResourceUrl, String.class);
    }
}
