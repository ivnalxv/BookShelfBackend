package ru.tinkoff.academy.bookshelf.controller;

import org.junit.jupiter.api.Test;
import org.springframework.test.web.reactive.server.WebTestClient;

public class SystemControllerTest extends BaseControllerTest {
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
        checkIfSystemStatusUp(response);
    }

}