package com.vini.dev.twapi.api.users.service;

import com.vini.dev.twapi.api.users.domain.User;
import com.vini.dev.twapi.api.users.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    @Transactional
    public User registerUser (final User user) throws Exception {
        return this.userRepository.save(user);
    }
}
