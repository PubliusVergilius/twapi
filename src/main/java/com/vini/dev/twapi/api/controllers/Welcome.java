package com.vini.dev.twapi.api.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class Welcome {

    @GetMapping
    String Presentation ()   {
        return "Olá!";
    }
}
