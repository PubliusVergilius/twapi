package com.vini.dev.twapi.api.posts.services;

import com.vini.dev.twapi.api.adaptars.JpaPostStore;
import com.vini.dev.twapi.api.adaptars.JpaUserStore;
import com.vini.dev.twapi.api.posts.domain.Post;
import com.vini.dev.twapi.api.posts.dto.PostCreateDTO;
import com.vini.dev.twapi.api.posts.dto.PostDTO;
import com.vini.dev.twapi.api.posts.exceptions.InexistentUserException;
import com.vini.dev.twapi.api.posts.mappers.PostMapper;
import com.vini.dev.twapi.api.users.domain.User;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
// @AllArgsConstructor
public class PostService {

    private final JpaPostStore postStore;
    private final JpaUserStore userStore;

    public PostService(JpaPostStore postRepository, final JpaUserStore userRepository) {
        this.postStore = postRepository;
        this.userStore = userRepository;
    }

    @Transactional
    public Optional<Post> registerPost ( PostCreateDTO post ) {
        Post postEntity = PostMapper.toEntity(post);

        String authorId = post.authorId();

        final Optional<User> optUser = this.userStore.findById(authorId);
        if (optUser.isEmpty()){
            throw new InexistentUserException(authorId);
        }
        var user = optUser.get();

        System.out.println("*********** New Post **************");

        postEntity.setAuthor(user);
        Post saved = this.postStore.save(postEntity);

        System.out.println(saved);
        return Optional.of(saved);

    }

    @Transactional
    public Optional<PostDTO> retrievePost (String postId) {
        Optional<Post> postOpt = this.postStore.findById(postId);
        if (postOpt.isPresent()) {
            var post = postOpt.get();
            return Optional.of(new PostDTO(post.getId(), post.getAuthor().getId(), post.getBody()));
        }
        return Optional.ofNullable(null);
    }
}
