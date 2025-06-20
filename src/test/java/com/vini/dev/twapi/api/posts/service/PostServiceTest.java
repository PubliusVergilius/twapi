package com.vini.dev.twapi.api.posts.service;

import com.vini.dev.twapi.api.posts.config.StubRepositoryConfig;
import com.vini.dev.twapi.api.posts.domain.Post;
import com.vini.dev.twapi.api.posts.dto.PostRequest;
import com.vini.dev.twapi.api.posts.dto.PostResponse;
import com.vini.dev.twapi.api.posts.repository.StubPostRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.dao.DataIntegrityViolationException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


/// Using h2 database instead of the stub give a more realistic scenario
/// @Deprecated
/// @Import(StubRepositoryConfig.class) // import test config with stub
@SpringBootTest
public class PostServiceTest {

    @Autowired
    private PostService postService;
    @Deprecated

    /// Using h2 database instead of the stub give a more realistic scenario
    ///@Deprecated
    ///@Autowired
    ///private StubPostRepository stubPostRepository;
    ///@Deprecated
    ///@BeforeEach
    ///public  void setUp () {
    ///stubPostRepository.clear();
    ///stubPostRepository.preload(new Post("1", "1", "teste"));
    ///}

    class Template {
        String message;
        Post want;
        boolean shouldError;

        public Template(String message, Post want, boolean shouldError) {
            this.message = message;
            this.want = want;
            this.shouldError = shouldError;
        }
    }

    @Test
    void  it_should_create_post () {
        List<Template> test_table = new ArrayList<>();
        test_table.add(new Template("should save and return first post",new Post("1", "1", "Teste"), false));
        test_table.add(new Template("should save and return second post",new Post("2", "1", "Teste"), false));
        test_table.forEach(test -> {
           try {
               Post want = new Post(test.want.getId(), test.want.getUserId(), test.want.getBody());
               PostResponse got = postService.registerPost(new PostRequest(want.getUserId(), want.getBody() ));

               Assertions.assertNotNull(got, "should never be null");
               Assertions.assertEquals(want.getBody(), got.body(), "wrong response body on registering new post");
               Assertions.assertEquals(want.getUserId(), got.userId(), "wrong user id on registering new post");
           } catch (Exception e) {
              Assertions.assertTrue(test.shouldError);
              Assertions.assertInstanceOf(DataIntegrityViolationException.class, e);
           }
        });
    }
    @Test
    void should_retrieve_post_by_id () {
        PostResponse got = postService.retrievePost("1");
        // Assertions.assertTrue(got., "post should not be null");
        Assertions.assertEquals(new PostResponse("1", "1", "teste 1"), got);
    }
}
