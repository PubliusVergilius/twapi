package com.vini.dev.twapi.api.posts;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.http.HttpResponse;

@RestController
@RequestMapping("/api/v1/posts")
public class Controller {

    @PostMapping(value = "/{userId}")
    ResponseEntity<Post> getAllPublicPostsByUserId (@PathVariable String userId, HttpServletResponse response) {
        Post newPost = new Post (userId, "Olá! Este é meu primeiro post.");
        return ResponseEntity.status(HttpStatus.CREATED).contentType(MediaType.APPLICATION_JSON).body(newPost);
    }
}
