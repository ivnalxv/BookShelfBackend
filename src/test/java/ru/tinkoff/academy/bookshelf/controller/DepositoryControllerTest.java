package ru.tinkoff.academy.bookshelf.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.reactive.server.WebTestClient;
import ru.tinkoff.academy.bookshelf.entity.Depository;
import ru.tinkoff.academy.bookshelf.service.DepositoryService;

import java.util.List;
import java.util.Random;


public class DepositoryControllerTest extends BaseControllerTest {
    @Autowired
    private DepositoryService depositoryService;
    private final Random random = new Random();

    private Depository takeRandomDepository() {
        List<Depository> depositories = depositoryService.getDepositories();
        return depositories.get(random.nextInt(depositories.size()));
    }

    private void checkIfResponseJsonEqualsDepositories(WebTestClient.ResponseSpec response) {
        checkIfResponseSuccessfulAndJson(response);
        WebTestClient.BodyContentSpec body = response.expectBody();
        List<Depository> depositories = depositoryService.getDepositories();
        for (int i = 0; i < depositories.size(); i++) {
            body.jsonPath(String.format("$.[%s].id", i)).isEqualTo(depositories.get(i).getId().toString());
        }
    }

    @Test
    public void getNearestByDepositoryIdTest() {
        // given
        Depository depository = takeRandomDepository();

        // when
        WebTestClient.ResponseSpec response = getResponseSpecFromUriBuilder(
            ub -> ub.path("/shelf/{id}/nearest").build(depository.getId())
        );

        // then
        checkIfResponseJsonEqualsDepositories(response);
    }

    @Test
    public void getNearestByCoordinatesTest() {
        // when
        WebTestClient.ResponseSpec response = getResponseSpecFromUriBuilder(
                ub -> ub.path("/shelf/nearest")
                        .queryParam("longitude", 0)
                        .queryParam("latitude", 0)
                        .build()
        );

        // then
        checkIfResponseJsonEqualsDepositories(response);
    }
}
