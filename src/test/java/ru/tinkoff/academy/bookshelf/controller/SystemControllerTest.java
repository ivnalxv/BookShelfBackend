package ru.tinkoff.academy.bookshelf.controller;

import org.junit.jupiter.api.Test;
import org.springframework.test.web.reactive.server.WebTestClient;

public class SystemControllerTest extends BaseControllerTest {

    private void thenSystemStatusUp(WebTestClient.ResponseSpec response) {
        checkIfResponseSuccessfulAndJson(response);
        response.expectBody().jsonPath("$.status").isEqualTo("UP");
    }

    private void thenSystemVersionCorrect(WebTestClient.ResponseSpec response) {
        checkIfResponseSuccessfulAndJson(response);
        response.expectBody().jsonPath("$.build.version").isEqualTo("0.0.1-SNAPSHOT");
    }

    @Test
    public void readinessTest() {
        // when
        WebTestClient.ResponseSpec response = whenReceiveResponseSpecFromURI("/system/readiness");

        // then
        thenSystemStatusUp(response);
    }

    @Test
    public void livenessTest() {
        // when
        WebTestClient.ResponseSpec response = whenReceiveResponseSpecFromURI("/system/liveness");

        // then
        thenSystemStatusUp(response);
    }

    @Test
    public void versionTest() {
        // when
        WebTestClient.ResponseSpec response = whenReceiveResponseSpecFromURI("/system/version");

        // then
        thenSystemVersionCorrect(response);
    }

}