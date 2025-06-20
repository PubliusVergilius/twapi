package com.vini.dev.twapi.api.posts.config;

import com.vini.dev.twapi.api.posts.domain.Post;
import com.vini.dev.twapi.api.posts.repository.StubPostRepository;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;

@TestConfiguration
public class StubRepositoryConfig {
    // @Bean(name = "userRepository")
    @Bean()
    @Primary
    public StubPostRepository stubPostRepository () {
        StubPostRepository stub = new StubPostRepository();
        stub.preload(
            new Post("1", "1", "teste 1"),
            new Post("1", "1", "teste 2")
        );
        return stub;
    }
}
