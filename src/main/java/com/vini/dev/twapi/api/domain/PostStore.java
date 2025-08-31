package com.vini.dev.twapi.api.domain;

import com.vini.dev.twapi.api.posts.domain.Post;
import com.vini.dev.twapi.api.posts.repositories.PostRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

public interface PostStore {
	List<Post> findAll();
	Optional<Post> findById(String id);
	Post save(Post post);
	void delete(Post post);
}
