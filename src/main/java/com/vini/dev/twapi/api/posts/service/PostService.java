package com.vini.dev.twapi.api.posts.service;

import com.vini.dev.twapi.api.posts.domain.Post;
import com.vini.dev.twapi.api.posts.dto.PostCreateDTO;
import com.vini.dev.twapi.api.posts.dto.PostDTO;
import com.vini.dev.twapi.api.posts.mapper.PostMapper;
import com.vini.dev.twapi.api.posts.repository.PostRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
// @AllArgsConstructor
public class PostService {

    private final PostRepository postRepository;

    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }
    @Transactional
    public PostDTO registerPost (PostCreateDTO post) {
        Post postEntity = PostMapper.toEntity(post);
        Post saved = postRepository.save(postEntity);

        return new PostDTO(saved.getId(), saved.getUserId(), saved.getBody());
    }

    @Transactional
    public Optional<PostDTO> retrievePost (String postId) {
        Optional<Post> postOpt = postRepository.findById(postId);
        if (postOpt.isPresent()) {
            var post = postOpt.get();
            return Optional.of(new PostDTO(post.getId(), post.getUserId(), post.getBody()));
        }
        return null;
    }
}
