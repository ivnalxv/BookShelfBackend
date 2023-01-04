package ru.tinkoff.academy.bookshelf.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/system")
@AllArgsConstructor
public class SystemController {
    private final WebClient client;

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
