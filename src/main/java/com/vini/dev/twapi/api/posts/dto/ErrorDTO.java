package com.vini.dev.twapi.api.posts.dto;

import com.vini.dev.twapi.api.posts.domain.Post;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.http.HttpStatus;

import java.util.List;

public record ErrorDTO (
      List<String> errors,
      // maps the errors to the exact fields
      @NotNull  Post data,
      @NotBlank  HttpStatus statusCode,
      @NotBlank String errorMessage,
      String cause
) {}
