package ru.tinkoff.academy.bookshelf.controller;

import org.junit.jupiter.api.Test;
import org.springframework.test.web.reactive.server.WebTestClient;

public class SystemControllerTest extends BaseControllerTest {

    private void checkIfSystemStatusUp(WebTestClient.ResponseSpec response) {
        checkIfResponseSuccessfulAndJson(response);
        response.expectBody().jsonPath("$.status").isEqualTo("UP");
    }

    private void checkIfSystemVersionCorrect(WebTestClient.ResponseSpec response) {
        checkIfResponseSuccessfulAndJson(response);
        response.expectBody().jsonPath("$.build.version").isEqualTo("0.0.1-SNAPSHOT");
    }

    @Test
    public void readinessTest() {
        // when
        WebTestClient.ResponseSpec response = getResponseSpecFromURI("/system/readiness");

        // then
        checkIfSystemStatusUp(response);
    }

    @Test
    public void livenessTest() {
        // when
        WebTestClient.ResponseSpec response = getResponseSpecFromURI("/system/liveness");

        // then
        checkIfSystemStatusUp(response);
    }

    @Test
    public void versionTest() {
        // when
        WebTestClient.ResponseSpec response = getResponseSpecFromURI("/system/version");

        // then
        checkIfSystemVersionCorrect(response);
    }

}