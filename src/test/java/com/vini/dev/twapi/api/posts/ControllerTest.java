package com.vini.dev.twapi.api.posts;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.nio.charset.StandardCharsets;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(Controller.class)
public class ControllerTest {
    @Autowired
    private MockMvc mock;

    @Autowired
    private ObjectMapper postMapper;

    @Test
    void PostController_ShouldCreateNewPost() throws  Exception {
        String userId = "1";
        String newPost = postMapper.writeValueAsString(new Post(userId, "Olá! Este é meu primeiro post."));
        mock.perform(post("/api/v1/posts/" + userId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(newPost))
                .andExpect(status().isCreated())
                .andExpect(MockMvcResultMatchers.content().string(newPost));
    }
}
