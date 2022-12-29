package ru.tinkoff.academy.bookshelf.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import ru.tinkoff.academy.bookshelf.entity.BookDepositDto;
import ru.tinkoff.academy.bookshelf.service.DepositoryService;
import java.util.UUID;

@RestController
@RequestMapping("/shelf")
@RequiredArgsConstructor
public class DepositoryController {
    private final DepositoryService depositoryService;

//    Который будет возвращать {amount} ближайшие хранилища книг
//    в радиусе {distance} к хранилищу с указанным {id}
    @GetMapping("/{id}/nearest")
    public Flux<BookDepositDto> getNearest(
            @PathVariable UUID id,
            @RequestParam(required = false, defaultValue = "100") Long distance,
            @RequestParam(required = false, defaultValue = "50") Long amount
    ) {
        return depositoryService.getNearestBookDeposits(
                id,
                distance,
                amount);
    }

//    Который будет возвращать {amount} ближайшие хранилища в радиусе {distance}
//    к указанным координатам определяемым {latitude} и {longitude}
//    переданными в параметрах запроса
    @GetMapping("/nearest")
    public Flux<BookDepositDto> getNearest(
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
