package ru.tinkoff.academy.bookshelf.controller;

import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import ru.tinkoff.academy.bookshelf.domain.dao.Depository;
import ru.tinkoff.academy.bookshelf.domain.dto.BookDeposit;
import ru.tinkoff.academy.bookshelf.service.DepositoryService;
import java.util.UUID;

@RestController
@RequestMapping("/shelf")
public class DepositoryController {
    private final DepositoryService depositoryService = new DepositoryService();

//    Который будет возвращать {amount} ближайшие хранилища книг
//    в радиусе {distance} к хранилищу с указанным {id}
    @GetMapping("/{id}/nearest")
    public Flux<BookDeposit> getNearest(
            @PathVariable UUID id,
            @RequestParam(required = false, defaultValue = "100") Long distance,
            @RequestParam(required = false, defaultValue = "50") Long amount
    ) {
        Depository depository = depositoryService.getDepositoryById(id);
        if (depository == null) {
            return Flux.just();
        }
        return depositoryService.getNearestBookDeposits(
                depository.getLatitude(),
                depository.getLongitude(),
                distance,
                amount);
    }

//    Который будет возвращать {amount} ближайшие хранилища в радиусе {distance}
//    к указанным координатам определяемым {latitude} и {longitude}
//    переданными в параметрах запроса
    @GetMapping("/nearest")
    public Flux<BookDeposit> getNearest(
            @RequestParam Double latitude,
            @RequestParam Double longitude,
            @RequestParam(required = false, defaultValue = "250") Long distance,
            @RequestParam(required = false, defaultValue = "10") Long amount
    ) {
        return depositoryService.getNearestBookDeposits(
                latitude,
                longitude,
                distance,
                amount);
    }
}
