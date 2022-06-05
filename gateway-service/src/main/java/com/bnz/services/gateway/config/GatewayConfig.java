package com.bnz.services.gateway.config;

import com.bnz.services.gateway.filters.JWTAuthFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfig {
    @Autowired
    private JWTAuthFilter filter;

    @Bean
    public RouteLocator routes(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("auth", r -> r.path("/auth/**").filters(f -> f.filter(filter)).uri("lb://auth-service"))
                .route("quiz", r -> r.path("/quiz/**").filters(f -> f.filter(filter)).uri("lb://quizzes-service"))
                .route("user", r -> r.path("/user/**").filters(f -> f.filter(filter)).uri("lb://user-service"))
                .build();
    }
}
