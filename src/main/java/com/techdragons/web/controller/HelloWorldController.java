package com.techdragons.web.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
@RestController
public class HelloWorldController {

    private static final Logger log = LoggerFactory.getLogger(HelloWorldController.class);

    @GetMapping("/hello")
    public String HelloWorld(){
        return "Hello World";
    }
}