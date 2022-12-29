package ru.tinkoff.academy.bookshelf.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfiguration {
    @Value("${default.url}")
    private String DEFAULT_LOCAL_URL;
    @Bean
    public WebClient createLocalWebClient() {
        return WebClient.create(DEFAULT_LOCAL_URL);
    }
}
