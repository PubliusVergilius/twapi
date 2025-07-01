package com.vini.dev.twapi.api.users.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vini.dev.twapi.api.lib.RequestProcessor;
import com.vini.dev.twapi.api.users.domain.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UserController.class)
public class UserControllerTest {
    @Autowired
    private MockMvc mock;
    private  final String uri = "/api/v1/users/";

    @Autowired
    private ObjectMapper userMapper;

    @Test
    void it_should_return_created_response () throws Exception {
        final User newUser = new User("vini");
        final String want = this.userMapper.writeValueAsString(newUser);

        final MvcResult got = this.mock.perform(post(this.uri)
                .content(this.userMapper.writeValueAsString(newUser))
                .with(new RequestProcessor().jsonDefaults()))
                .andExpect(status().isCreated())
                .andReturn();

        this.assertResponseContent(got, want);
    }

    void assertResponseContent (final MvcResult got, final String want) {
        final String responseContent = new String(got.getResponse().getContentAsByteArray(), StandardCharsets.UTF_8);
        assertEquals(want, responseContent);
    }
}
