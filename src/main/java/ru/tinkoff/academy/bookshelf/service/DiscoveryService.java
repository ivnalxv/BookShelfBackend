package ru.tinkoff.academy.bookshelf.service;

import reactor.core.publisher.Mono;

public interface DiscoveryService {
    Mono<String> discoverService(String url);
    Mono<String> getAllServices();
}
