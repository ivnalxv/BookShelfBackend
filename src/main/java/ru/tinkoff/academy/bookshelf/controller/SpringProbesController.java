package ru.tinkoff.academy.bookshelf.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/system")
public class SpringProbesController {
    @Autowired
    private ApplicationContext context;
    private final String DEFAULT_LOCAL_URL = "http://localhost:8080";
    private final WebClient client = WebClient.create(DEFAULT_LOCAL_URL);

    private Mono<String> getMonoResponseByURI(String request) {
        return client.get().uri(request).retrieve().bodyToMono(String.class);
    }

    @GetMapping("liveness")
    public Mono<String> getLiveness() {
        return getMonoResponseByURI("/actuator/health/liveness");
    }

    @GetMapping("readiness")
    public Mono<String> getReadiness() {
        return getMonoResponseByURI("/actuator/health/readiness");
    }

    @GetMapping("version")
    public Mono<String> getVersion() {
        return getMonoResponseByURI("/actuator/info");
    }
}
