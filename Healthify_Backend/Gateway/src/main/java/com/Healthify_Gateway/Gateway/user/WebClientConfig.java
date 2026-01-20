package com.Healthify_Gateway.Gateway.user;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {

    @Bean

    public WebClient.Builder webClientBuilder(){
        return WebClient.builder();
    }

    @Bean
    public WebClient userServiceWebClient(WebClient.Builder webClinetBuilder){
        return webClinetBuilder
                .baseUrl("http://localhost:9090")
                .build();
    }
}
