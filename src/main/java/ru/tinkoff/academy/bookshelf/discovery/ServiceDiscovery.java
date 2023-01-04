package ru.tinkoff.academy.bookshelf.discovery;

import reactor.core.publisher.Mono;

public interface ServiceDiscovery {
    Mono<String> discoverService(String url);
}
