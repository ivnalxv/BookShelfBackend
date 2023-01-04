package ru.tinkoff.academy.bookshelf.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import java.util.UUID;

@Getter
@Builder
@AllArgsConstructor
public class BookDepositDto {
    private UUID id;
    @Builder.Default
    private String nick = "placeholder";
    @Builder.Default
    private String address = "Address hidden";
    @Builder.Default
    private String description = "No description provided";
    @Builder.Default
    private String type = "bookshelf";
    private String location;
}
