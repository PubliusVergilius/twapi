package com.vini.dev.twapi.api.controllers;

import jdk.jshell.Snippet;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(Welcome.class)
public class WelcomeTest {

    @Autowired
    private MockMvc mock;

    @Test
    void Presentation_ShouldReturnWelcome() throws  Exception {
        mock.perform(get("/api/v1"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("Olá!"));
    }
}
