package ru.tinkoff.academy.bookshelf.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.util.UriBuilder;

import java.net.URI;
import java.util.function.Function;


@AutoConfigureWebTestClient
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class BaseControllerTest {
    @Autowired
    protected WebTestClient webTestClient;

    protected WebTestClient.ResponseSpec getResponseSpecFromURI(String uri) {
        return webTestClient.get().uri(uri).accept(MediaType.APPLICATION_JSON).exchange();
    }

    protected WebTestClient.ResponseSpec getResponseSpecFromUriBuilder(Function<UriBuilder, URI> builder) {
        return webTestClient.get().uri(builder).accept(MediaType.APPLICATION_JSON).exchange();
    }

    protected void checkIfResponseSuccessful(WebTestClient.ResponseSpec response) {
        response.expectStatus().is2xxSuccessful();
    }

    protected void checkIfResponseJson(WebTestClient.ResponseSpec response) {
        response.expectHeader().contentType(MediaType.APPLICATION_JSON);
    }

    protected void checkIfResponseSuccessfulAndJson(WebTestClient.ResponseSpec response) {
        checkIfResponseSuccessful(response);
        checkIfResponseJson(response);
    }
}
