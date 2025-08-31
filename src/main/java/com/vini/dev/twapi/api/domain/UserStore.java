package com.vini.dev.twapi.api.domain;

import com.vini.dev.twapi.api.users.domain.User;

import java.util.Optional;

public interface UserStore {
	Optional<User> findById(String id);
	User save(User user);
	void delete(User user);
}
