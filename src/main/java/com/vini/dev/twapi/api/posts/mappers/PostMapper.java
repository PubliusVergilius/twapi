package com.vini.dev.twapi.api.posts.mappers;

import com.vini.dev.twapi.api.posts.domain.Post;
import com.vini.dev.twapi.api.posts.dto.PostCreateDTO;
import com.vini.dev.twapi.api.posts.dto.PostResponseDTO;
import com.vini.dev.twapi.api.users.domain.User;

public class PostMapper {

    public static Post toEntity (final PostCreateDTO dto) {
        final User user = new User();
        user.setId(dto.authorId());
        return new Post(null, user, dto.body());
    }

    public static PostResponseDTO toResponse (final Post post) {
       return new PostResponseDTO(post.getId(), post.getAuthor().getId(), post.getBody());
    }

    public static String toHal (final PostResponseDTO post) {
        return null;
    }
}
