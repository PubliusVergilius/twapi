package com.vini.dev.twapi.api.posts.exceptions;

import org.springframework.dao.DataIntegrityViolationException;

public class InexistentUserException extends RuntimeException {
	public InexistentUserException (String userId) {
		super(String.format("User does not exist with id: %s", userId));
	};

	static public boolean isInexistentUserIDError (DataIntegrityViolationException e) {
		Throwable cause = e.getCause();
		return cause != null && cause.getMessage() != null &&
				cause.getMessage().contains("users.id");
	}
}
