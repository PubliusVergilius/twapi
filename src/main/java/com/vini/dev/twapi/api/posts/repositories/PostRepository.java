package com.vini.dev.twapi.api.posts.repositories;


import com.vini.dev.twapi.api.posts.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<Post, String> {
}
