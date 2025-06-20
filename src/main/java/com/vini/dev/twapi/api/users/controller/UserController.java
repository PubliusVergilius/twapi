package com.vini.dev.twapi.api.users.controller;

import com.vini.dev.twapi.api.users.domain.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/v1/users")
public class UserController {
    @PostMapping
    ResponseEntity<User> createUser () {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new User("vini"));
    }
}
