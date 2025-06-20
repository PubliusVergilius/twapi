package com.vini.dev.twapi.api.posts.dto;

import jakarta.validation.constraints.NotBlank;

public record PostResponse (
        @NotBlank String id,
        @NotBlank String userId,
        @NotBlank  String body
) {}
