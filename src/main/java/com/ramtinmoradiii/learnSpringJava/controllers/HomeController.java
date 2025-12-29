package com.ramtinmoradiii.learnSpringJava.controllers;

import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/home")
public class HomeController {

    @GetMapping("")
    public String hello(@CookieValue(value = "name", defaultValue = "World") String name) {
        return "Hello " + name;
    }
}
