package com.vini.dev.twapi.api.posts.services;

import com.vini.dev.twapi.api.posts.domain.Post;
import com.vini.dev.twapi.api.posts.dto.PostCreateDTO;
import com.vini.dev.twapi.api.posts.dto.PostDTO;
import com.vini.dev.twapi.api.posts.exceptions.InexistentUserException;
import com.vini.dev.twapi.api.posts.mappers.PostMapper;
import com.vini.dev.twapi.api.posts.repositories.PostRepository;
import com.vini.dev.twapi.api.users.domain.User;
import com.vini.dev.twapi.api.users.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Service
// @AllArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final UserRepository userRepository;

    public PostService(PostRepository postRepository, final UserRepository userRepository) {
        this.postRepository = postRepository;
        this.userRepository = userRepository;
    }

    @Transactional
    public Optional<Post> registerPost ( PostCreateDTO post ) {
        Post postEntity = PostMapper.toEntity(post);

        String authorId = post.authorId();

        final Optional<User> optUser = this.userRepository.findById(authorId);
        if (optUser.isEmpty()){
            throw new InexistentUserException(authorId);
        }
        var user = optUser.get();

        System.out.println("*********** New Post **************");

        postEntity.setAuthor(user);
        Post saved = this.postRepository.save(postEntity);

        System.out.println(saved);
        return Optional.of(saved);

    }

    @Transactional
    public Optional<PostDTO> retrievePost (String postId) {
        Optional<Post> postOpt = this.postRepository.findById(postId);
        if (postOpt.isPresent()) {
            var post = postOpt.get();
            return Optional.of(new PostDTO(post.getId(), post.getAuthor().getId(), post.getBody()));
        }
        return Optional.ofNullable(null);
    }
}
