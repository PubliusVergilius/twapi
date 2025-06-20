package com.vini.dev.twapi.api.posts.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vini.dev.twapi.api.lib.RequestProcessor;
import com.vini.dev.twapi.api.posts.controllers.PostController;
import com.vini.dev.twapi.api.posts.domain.Post;
import com.vini.dev.twapi.api.posts.dto.PostResponse;
import com.vini.dev.twapi.api.posts.service.PostService;
import jakarta.servlet.http.Cookie;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultMatcher;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * All tests here is to assert  HAL response format ( strings ) and the controller's behavior.
 * The controller should be tested in isolation not querying from the database;
 */


@WebMvcTest(PostController.class)
public class PostControllerTest {
    @Autowired
    private MockMvc mock;
    @MockBean
    private PostService postService;

    private final String uri = "/api/v1/posts/";

    @Autowired
    private ObjectMapper postMapper;

    @Test
    void it_should_return_created_response() throws  Exception {

        class Template {
            String name;
            PostResponse want;
            ResultMatcher got;

            public Template(String name, PostResponse want, ResultMatcher got) {
                this.name = name;
                this.want = want;
                this.got = got;
            }
        }
        // remove it from here: should be testing response format, status; not ACID operations
        List<Template> table = new ArrayList<>();
        table.add(new Template(
                "Should get post 1 with 201",
                new PostResponse("1" , "1","teste 1"),
                status().isCreated()
        ));

        table.add(new Template(
                "Should get post 2 with 201",
                new PostResponse("2" , "1","teste 2"),
                status().isCreated()
        ));

        for (Template test : table) {
            String json = postMapper.writeValueAsString(test.want);
            MvcResult result = mock.perform(post(uri)
                            .cookie(new Cookie("userId", test.want.userId()))
                            .content(json)
                            .with(new RequestProcessor().jsonDefaults()))
                    .andExpect(test.got)
                    .andReturn();
            assertResponseContent(result, test.want.body(), test.name);
        }
    }

    @Test
    void should_get_post_response_with_found_status() throws Exception {
        List<Post> posts = new ArrayList<Post>();
        posts.add(new Post("1" , "1","teste 1"));

        String want = postMapper.writeValueAsString(posts);
        MvcResult got = mock.perform(get(uri + "1")
                        .cookie(new Cookie("userId", "1")))
                .andExpect(status().isFound())
                .andReturn();

        assertResponseContent(got, "teste 1", "should get post 1 with status ok");
    }

    void assertResponseContent (MvcResult got, String want, String message) {
        String responseContent = new String(got.getResponse().getContentAsByteArray(), StandardCharsets.UTF_8);

        System.out.println(responseContent);
        System.out.println(want);
        Assertions.assertTrue(responseContent.contains(new String(want.getBytes(StandardCharsets.UTF_8))), message);
        // assertEquals(want, responseContent, message);
    }
}

