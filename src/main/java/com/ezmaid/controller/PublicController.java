package com.ezmaid.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PublicController {

    @GetMapping("/home")
    public String getNumberOfUsers() {
        return "Home page!";
    }
    
    @GetMapping("/contactUs")
    public String getContactUs() {
        return "Contact Us page!";
    }
    
    @GetMapping("/aboutUs")
    public String getAboutUs() {
        return "About Us page!";
    }
}
