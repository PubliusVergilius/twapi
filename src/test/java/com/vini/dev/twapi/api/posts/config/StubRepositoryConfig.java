package com.vini.dev.twapi.api.posts.config;

import com.vini.dev.twapi.api.posts.domain.Post;
import com.vini.dev.twapi.api.posts.repositories.StubPostRepository;
import com.vini.dev.twapi.api.users.domain.User;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;

@TestConfiguration
public class StubRepositoryConfig {
    // @Bean(name = "userRepository")
    @Bean
    @Primary
    public StubPostRepository stubPostRepository () {
        final StubPostRepository stub = new StubPostRepository();
        final User user = new User("developer");
        user.setId("1");
        stub.preload(
            new Post("1", user, "teste 1"),
            new Post("1", user, "teste 2")
        );
        return stub;
    }
}
