package com.bnz.services.quizzes.beans;

import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.function.client.WebClient;

public class WebClientBean {
    @Bean
    public static WebClient localApiClient() {
        return WebClient.create("http://localhost:3000/");
    }
}
