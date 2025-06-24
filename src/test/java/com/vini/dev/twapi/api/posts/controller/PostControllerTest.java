package com.vini.dev.twapi.api.posts.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vini.dev.twapi.api.lib.RequestProcessor;
import com.vini.dev.twapi.api.posts.controllers.PostController;
import com.vini.dev.twapi.api.posts.domain.Post;
import com.vini.dev.twapi.api.posts.dto.PostCreateDTO;
import com.vini.dev.twapi.api.posts.dto.PostDTO;
import com.vini.dev.twapi.api.posts.service.PostService;
import jakarta.servlet.http.Cookie;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.ResultMatcher;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * All tests here is to assert  HAL response format ( strings ) and the controller's behavior.
 * The controller should be tested in isolation not querying from the database;
 */

@WebMvcTest(PostController.class)
@AutoConfigureMockMvc
public class PostControllerTest {
    @Autowired
    private MockMvc mock;

    List<Post> posts = new ArrayList<>();

    @MockBean
    private PostService postService;

    @BeforeEach
    void setUp () {
        posts = List.of(
                new Post("101", "1", "teste 1"),
                new Post("102", "1", "teste 2")
        );
    }

    private final String uri = "/api/v1/posts/";

    @Autowired
    private ObjectMapper postMapper;

    @Test
    void create_post_test_cases() throws  Exception {

        class Template {
            final String name;
            final PostCreateDTO input;
            final int expectedStatus;
            final Consumer<ResultActions> assertResponse;

            public Template(String name, PostCreateDTO input, int expectedStatus, Consumer<ResultActions> assertResponse) {
                this.name = name;
                this.input = input;
                this.expectedStatus = expectedStatus;
                this.assertResponse = assertResponse;
            }
        }

        List<Template> table_cases = List.of(
                new Template(
                        "Should get post 1 with 201",
                        new PostCreateDTO("1" ,  "teste 1"),
                        HttpStatus.CREATED.value(),
                        result -> {}
                ),
                new Template(
                        "Should get post 2 with 201",
                        new PostCreateDTO("1" , "teste 2"),
                        HttpStatus.CREATED.value(),
                        result -> {
                            try {
                                result.andExpect(jsonPath("$.id").isNotEmpty())
                                        .andExpect(jsonPath("$.body").value("teste 2"));
                            } catch (Exception e) {
                                throw new RuntimeException(e);
                            }
                        }
                ),
                new Template(
                        "should get 400 status",
                        new PostCreateDTO("", ""),
                        HttpStatus.BAD_REQUEST.value(),
                        result -> {
                            try {
                                result
                                        .andExpect(jsonPath("$.message").value("Validation failed"))
                                        .andExpect(jsonPath("$.errors").hasJsonPath());

                            } catch (Exception e) {
                                throw new RuntimeException(e);
                            }
                        }
                )
        );

        for (Template test : table_cases) {

            // Mock http response
            when(postService.registerPost(test.input))
                    .thenReturn(new PostDTO("teste", test.input.authorId(), test.input.body()));

            // Test request
            String json = postMapper.writeValueAsString(test.input);
            MvcResult result = mock.perform(post(uri)
                            .cookie(new Cookie("userId", test.input.authorId()))
                            .content(json)
                            .with(new RequestProcessor().jsonDefaults()))
                    .andExpect(status().is(test.expectedStatus))
                    .andReturn();

            // Test content
            assertResponseContent(result, test.input.body(), test.name);
            test.assertResponse.accept(mock.perform(post(uri)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(postMapper.writeValueAsString(test.input))));
        }
    }

    @Test
    void should_get_response_with_found_status() throws Exception {
        var want = new PostDTO("1", "1", "teste 1");
        String json = postMapper.writeValueAsString(want);

        when(postService.retrievePost("1")).thenReturn(Optional.of(want));
        MvcResult got = mock.perform(get(uri + "1")
                        .cookie(new Cookie("userId", "1")))
                .andExpect(status().isFound())
                .andReturn();

        assertResponseContent(got, json, "should get post 1 with status ok");
    }

    void assertResponseContent (MvcResult got, String want, String message) {
        String responseContent = new String(got.getResponse().getContentAsByteArray(), StandardCharsets.UTF_8);
        Assertions.assertTrue(responseContent.contains(new String(want.getBytes(StandardCharsets.UTF_8))), message);
    }
}

