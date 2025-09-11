package com.vini.dev.twapi.api.users.service;

import com.vini.dev.twapi.api.domain.UserStore;
import com.vini.dev.twapi.api.users.domain.User;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService {

    private final UserStore userStore;

	public UserServiceResult<List<User>> retrieveAllUsers() {
		List<User> users = userStore.findAll();
		if (users.isEmpty()) {
			return new UserServiceResult<>(null, UserServiceErrorMessage.noUserFound());
		}
		return new UserServiceResult<>(users, null);
	}

	public UserServiceResult<User> retrieveUserById(String id) {
		Optional<User> userOpt = userStore.findById(id);
		if (userOpt.isEmpty()) {
			return new UserServiceResult(null, UserServiceErrorMessage.inexistentUser());
		}
		var user = userOpt.get();
		return new UserServiceResult<User>(user, null);
	}

    public User registerUser (final User user) throws Exception {
        return this.userStore.save(user);
    }
}
