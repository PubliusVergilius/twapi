package com.vini.dev.twapi.api.lib;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.RequestPostProcessor;

import java.nio.charset.StandardCharsets;

public class RequestProcessor {

    public RequestPostProcessor jsonDefaults () {
        return request -> {
            request.setContentType(MediaType.APPLICATION_JSON_VALUE);
            request.setCharacterEncoding(StandardCharsets.UTF_8.name());
            request.addHeader(HttpHeaders.ACCEPT, "application/json;charset=UTF-8");
            return request;
        };
    }
}
