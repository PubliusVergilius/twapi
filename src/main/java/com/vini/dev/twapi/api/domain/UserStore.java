package com.vini.dev.twapi.api.domain;

import com.vini.dev.twapi.api.users.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserStore {
	List<User> findAll();
	Optional<User> findById(String id);
	User save(User user);
	void delete(User user);
}
