package ru.tinkoff.academy.bookshelf.service;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Map;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class BookCrossingDiscoveryService implements DiscoveryService {
    @Value("#{${services.list}}")
    private Map<String, String> serviceNameToURL;

    private static final String ERROR_MESSAGE = "\"is not available\"";
    private static final String STATUS_URI = "system/liveness";
    private static final String VERSION_URI = "system/version";
    private Mono<String> getMonoStringByURI(WebClient client, String uri) {
        return client.get().uri(uri).retrieve().bodyToMono(String.class);
    }

    @Override
    public Mono<String> discoverService(String url) {
        WebClient client = WebClient.create(url);

        try {
            Mono<String> version = getMonoStringByURI(client, VERSION_URI);
            return getMonoStringByURI(client, STATUS_URI)
                    .filter(status -> !status.equals("{\"status\":\"UP\"}"))
                    .switchIfEmpty(version)
                    .onErrorReturn(ERROR_MESSAGE);
        } catch (Exception e) {
            return Mono.just(ERROR_MESSAGE);
        }
    }

    public Mono<String> getAllServices() {
        return Flux.fromStream(serviceNameToURL.entrySet().stream())
                .flatMap(entry -> discoverService(entry.getValue())
                        .map(response -> String.format("\"%s\" : %s", entry.getKey(), response))
                )
                .collect(Collectors.joining(", ", "{", "}"));
    }

}
