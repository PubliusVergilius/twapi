package com.vini.dev.twapi.api.posts.controllers;

import com.vini.dev.twapi.api.posts.domain.Post;
import com.vini.dev.twapi.api.posts.dto.PostRequest;
import com.vini.dev.twapi.api.posts.dto.PostResponse;
import com.vini.dev.twapi.api.posts.service.PostService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/posts")
public class PostController {

    PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping(value = "/{postId}")
    ResponseEntity<PostResponse> getPostById  (@PathVariable String postId) {
        PostResponse post = postService.retrievePost(postId);
        return ResponseEntity.status(HttpStatus.FOUND)
                .contentType(MediaType.APPLICATION_JSON).body(post);
    }

    @PostMapping
    ResponseEntity<PostResponse> createPost (@CookieValue(value = "userId", defaultValue = "") String userId, @RequestBody PostRequest request) {
        PostResponse response = postService.registerPost(new PostRequest(userId, request.body()));

        return ResponseEntity.status(HttpStatus.CREATED)
                .contentType(MediaType.APPLICATION_JSON).body(response);
    }
}
