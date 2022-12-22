package ru.tinkoff.academy.bookshelf.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
public class BookDepositDto {
    private UUID id;
    private String nick;
    private String address;
    private String description;
    private String type;
    private String location;
}
