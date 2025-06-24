package com.vini.dev.twapi.api.posts.controllers;

import com.vini.dev.twapi.api.posts.dto.PostCreateDTO;
import com.vini.dev.twapi.api.posts.dto.PostDTO;
import com.vini.dev.twapi.api.posts.service.PostService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/posts")
public class PostController {

    @Autowired
    PostService postService;

    @GetMapping(value = "/{postId}")
    ResponseEntity<PostDTO> getPostById  (@PathVariable String postId) {
        PostDTO post = postService.retrievePost(postId);

        return ResponseEntity.status(HttpStatus.FOUND)
                .contentType(MediaType.APPLICATION_JSON).body(post);
    }

    @PostMapping
    ResponseEntity<PostDTO> createPost (@CookieValue(value = "userId", defaultValue = "") String userId,
                                        @Valid @RequestBody PostCreateDTO request) {
        PostDTO response = postService.registerPost(request);

        return ResponseEntity.status(HttpStatus.CREATED)
                .contentType(MediaType.APPLICATION_JSON).body(response);
    }
}
