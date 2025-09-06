package com.vini.dev.twapi.api.users.service;

import com.vini.dev.twapi.api.domain.UserStore;
import com.vini.dev.twapi.api.users.domain.User;
import com.vini.dev.twapi.api.users.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UserService {

    private final UserStore userStore;

	public List<User> retrieveAllUsers() {
		List<User> users = userStore.findAll();
		return users;
	}

    public User registerUser (final User user) throws Exception {
        return this.userStore.save(user);
    }
}
