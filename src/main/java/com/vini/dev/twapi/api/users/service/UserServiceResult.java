package com.vini.dev.twapi.api.users.service;

class UserServiceResult<T> {
	private final T value;
	private final String message;

	public UserServiceResult (T value, String message) {
		this.value = value;
		this.message = message;
	}

	public T get() {
		return value;
	}

	public String error() {
		return message;
	}

	public boolean isError() {
		return message != null && message.length() > 0;
	}
}
