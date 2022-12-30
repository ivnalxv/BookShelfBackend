package ru.tinkoff.academy.bookshelf.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import java.util.UUID;

@Getter
@Builder
@AllArgsConstructor
public class BookDepositDto {
    private UUID id;
    private String nick;
    @Builder.Default private String address = "Address hidden";
    @Builder.Default private String description = "No description provided";
    @Builder.Default private String type = "bookshelf";
    private String location;
}
