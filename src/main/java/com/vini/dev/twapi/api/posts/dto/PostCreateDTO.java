package com.vini.dev.twapi.api.posts.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record PostCreateDTO (
		@NotBlank(message = "An author must be assigned to a post")
		String authorId,

		@NotBlank
		@Size(min = 1, message = "Content must be at least 1 character long")
		String body
) {
}
