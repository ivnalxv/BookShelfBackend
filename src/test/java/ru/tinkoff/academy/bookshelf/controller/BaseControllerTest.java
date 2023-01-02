package ru.tinkoff.academy.bookshelf.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.util.Random;

@AutoConfigureWebTestClient
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class BaseControllerTest {
    @Autowired
    protected WebTestClient webTestClient;
    protected final Random random = new Random();

    protected WebTestClient.ResponseSpec getResponseSpecFromURI(String uri) {
        return webTestClient.get().uri(uri).accept(MediaType.APPLICATION_JSON).exchange();
    }

    protected void checkIfResponseSuccessful(WebTestClient.ResponseSpec response) {
        response.expectStatus().is2xxSuccessful();
    }

    protected void checkIfResponseJson(WebTestClient.ResponseSpec response) {
        response.expectHeader().contentType(MediaType.APPLICATION_JSON);
    }

    protected void checkIfSystemStatusUp(WebTestClient.ResponseSpec response) {
        checkIfResponseSuccessful(response);
        checkIfResponseJson(response);
        response.expectBody().json("{\"status\": \"UP\"}");
    }




}
