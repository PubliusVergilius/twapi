package com.vini.dev.twapi.api.posts.service;

import com.vini.dev.twapi.api.posts.domain.Post;
import com.vini.dev.twapi.api.posts.dto.PostRequest;
import com.vini.dev.twapi.api.posts.dto.PostResponse;
import com.vini.dev.twapi.api.posts.repository.PostRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
// @AllArgsConstructor
public class PostService {

    private final PostRepository postRepository;

    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
        System.out.println("Injected repo on postService: " + postRepository.getClass());
    }
    @Transactional
    public PostResponse registerPost (PostRequest postReq) {
        Post post = new Post("", postReq.userId(), postReq.body());
        Post saved = postRepository.save(post);
        return new PostResponse(saved.getId(), saved.getUserId(), saved.getBody());
    }

    @Transactional
    public PostResponse retrievePost (String postId) {
        Post post = postRepository.findById(postId).orElse(new Post());
        return new PostResponse(post.getId(), post.getUserId(), post.getBody());
    }
}
