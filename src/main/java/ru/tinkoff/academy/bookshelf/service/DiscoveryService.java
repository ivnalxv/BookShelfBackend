package ru.tinkoff.academy.bookshelf.service;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.tinkoff.academy.bookshelf.discovery.ServiceDiscovery;

import java.util.Map;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class DiscoveryService {
    @Value("#{${services.list}}")
    private Map<String, String> serviceNameToURL;
    private ServiceDiscovery serviceDiscovery;

    public Mono<String> getAllServices() {
        return Flux.fromStream(serviceNameToURL.entrySet().stream())
                .flatMap(entry -> serviceDiscovery
                        .discoverService(entry.getValue())
                        .map(response -> String.format("\"%s\" : %s", entry.getKey(), response))
                )
                .collect(Collectors.joining(", ", "{", "}"));
    }

}
