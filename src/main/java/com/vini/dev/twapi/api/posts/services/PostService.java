package com.vini.dev.twapi.api.posts.services;

import com.vini.dev.twapi.api.posts.domain.Post;
import com.vini.dev.twapi.api.posts.dto.PostCreateDTO;
import com.vini.dev.twapi.api.posts.dto.PostDTO;
import com.vini.dev.twapi.api.posts.mappers.PostMapper;
import com.vini.dev.twapi.api.posts.repositories.PostRepository;
import com.vini.dev.twapi.api.users.domain.User;
import com.vini.dev.twapi.api.users.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
// @AllArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final UserRepository userRepository;

    public PostService(final PostRepository postRepository, final UserRepository userRepository) {
        this.postRepository = postRepository;
        this.userRepository = userRepository;
    }
    @Transactional
    public PostDTO registerPost (final PostCreateDTO post) throws Exception {
        final Post postEntity = PostMapper.toEntity(post);

        final User user = this.userRepository.getReferenceById(post.authorId());
        postEntity.setAuthor(user);

        final Post saved = this.postRepository.save(postEntity);

        return new PostDTO(saved.getId(), saved.getAuthor().getId(), saved.getBody());
    }

    @Transactional
    public Optional<PostDTO> retrievePost (final String postId) {
        final Optional<Post> postOpt = this.postRepository.findById(postId);
        if (postOpt.isPresent()) {
            final var post = postOpt.get();
            return Optional.of(new PostDTO(post.getId(), post.getAuthor().getId(), post.getBody()));
        }
        return null;
    }
}
