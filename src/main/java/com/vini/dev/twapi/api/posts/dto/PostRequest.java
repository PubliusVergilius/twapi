package com.vini.dev.twapi.api.posts.dto;

import jakarta.validation.constraints.NotBlank;

public record PostRequest(
        @NotBlank  String userId,
        @NotBlank  String body
) {}
