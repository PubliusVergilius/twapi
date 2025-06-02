package com.vini.dev.twapi.gateway;


import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.RouteMatcher;
import org.springframework.web.servlet.function.RouterFunction;
import org.springframework.web.servlet.function.ServerRequest;
import org.springframework.web.servlet.function.ServerResponse;

import java.util.function.Function;

import static org.springframework.web.servlet.function.RouterFunctions.route;

@Configuration
public class GatewayConfig {
    @Bean
    RouteLocator routes(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("twapi_routes", r -> r.path("/api/v1/**")
                        .uri("http://localhost:9090"))
                .build();
    }
}
