package com.vini.dev.twapi.api.posts.mapper;

import com.vini.dev.twapi.api.posts.domain.Post;
import com.vini.dev.twapi.api.posts.dto.PostCreateDTO;
import com.vini.dev.twapi.api.posts.dto.PostResponseDTO;

public class PostMapper {
    public static Post toEntity (PostCreateDTO dto) {
        return new Post(null, dto.authorId(), dto.body());
    }

    public static PostResponseDTO toResponse (Post post) {
       return new PostResponseDTO(post.getId(), post.getUserId(), post.getBody());
    }

    public static String toHal (PostResponseDTO post) {
        return null;
    }
}
