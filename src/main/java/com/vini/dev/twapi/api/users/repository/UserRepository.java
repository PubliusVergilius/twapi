package com.vini.dev.twapi.api.users.repository;

import com.vini.dev.twapi.api.users.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository  extends JpaRepository<User, String> {
}
