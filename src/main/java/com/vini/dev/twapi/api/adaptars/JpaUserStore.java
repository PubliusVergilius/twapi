package com.vini.dev.twapi.api.adaptars;

import com.vini.dev.twapi.api.domain.UserStore;
import com.vini.dev.twapi.api.users.domain.User;
import com.vini.dev.twapi.api.users.repository.UserRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class JpaUserStore implements UserStore {

	private final UserRepository repository;

	public JpaUserStore (UserRepository repository) {
		this.repository = repository;
	}

	@Override
	public List<User> findAll () {
		return repository.findAll();
	}

	@Override
	public Optional<User> findById (String id) {
		return repository.findById(id);
	}

	@Override
	public User save(User user) {
		return repository.save(user);
	}

	@Override
	public void delete(User user) {
		repository.delete(user);
	}
}
