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
    /*
    @Bean
    @Primary
    public StubPostRepository stubPostRepository () {
        final StubPostRepository stub = new StubPostRepository();
        final User user = new User("developer");
        user.setId("111");
        stub.preload(
            new Post("111", "teste 1", user),
            new Post("111", "teste 2" ,user)
        );
        return stub;
    }
    */
}
