package com.vini.dev.twapi.api.adaptars;


import com.vini.dev.twapi.api.domain.PostStore;
import com.vini.dev.twapi.api.domain.UserStore;
import com.vini.dev.twapi.api.posts.domain.Post;
import com.vini.dev.twapi.api.posts.repositories.PostRepository;
import com.vini.dev.twapi.api.users.domain.User;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class JpaPostStore implements PostStore {

	private final PostRepository repository;

	public JpaPostStore (PostRepository repository) {
		this.repository = repository;
	}

	@Override
	public Optional<Post> findById (String id) {
		return repository.findById(id);
	}

	@Override
	public List<Post> findAll() {
		return repository.findAll();
	}

	@Override
	public Post save (Post post) {
		return repository.save(post);
	}

	@Override
	public void delete (Post post) {
		repository.delete(post);
	}
}
