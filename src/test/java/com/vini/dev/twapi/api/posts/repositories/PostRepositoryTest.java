package com.vini.dev.twapi.api.posts.repositories;


import com.vini.dev.twapi.api.posts.domain.Post;

import java.util.Optional;

public interface PostRepositoryTest  {
    Optional<Post> findById (String id);
    void save (Post post);
}
