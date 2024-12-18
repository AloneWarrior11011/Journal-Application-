package com.codewarrior.Journal_App.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthCheck {
    @GetMapping("/healthCheck")
    public String healthCheck(){
        return "Ok...All is Well";
    }
}
