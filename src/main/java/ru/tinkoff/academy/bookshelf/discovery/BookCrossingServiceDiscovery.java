package ru.tinkoff.academy.bookshelf.discovery;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;


@Component
@RequiredArgsConstructor
public class BookCrossingServiceDiscovery implements ServiceDiscovery {
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
}
