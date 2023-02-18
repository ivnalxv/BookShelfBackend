package ru.tinkoff.academy.bookshelf.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;
import ru.tinkoff.academy.bookshelf.service.DiscoveryService;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class DiscoveryController {
    private DiscoveryService discoveryService;

    @GetMapping(value = "discovery", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<String> getAllServices() {
        return discoveryService.getAllServices();
    }
}
