package com.vini.dev.twapi.api.users.dto;

import jakarta.validation.constraints.NotBlank;

public  record UserRequest(@NotBlank  String username) {}
