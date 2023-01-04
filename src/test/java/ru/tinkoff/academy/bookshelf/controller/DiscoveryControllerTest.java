package ru.tinkoff.academy.bookshelf.controller;

import org.junit.jupiter.api.Test;
import org.springframework.test.web.reactive.server.WebTestClient;

public class DiscoveryControllerTest extends BaseControllerTest {
    private void thenServicesResponsesExist(WebTestClient.ResponseSpec response) {
        checkIfResponseSuccessfulAndJson(response);
        response.expectBody()
                .jsonPath("$.bookhunter").exists()
                .jsonPath("$.blackbooks").exists();
    }

    @Test
    public void discoveryTest() {
        // when
        WebTestClient.ResponseSpec response = whenReceiveResponseSpecFromURI("/api/discovery");

        // then
        thenServicesResponsesExist(response);
    }
}
