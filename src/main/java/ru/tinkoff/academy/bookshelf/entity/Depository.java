package ru.tinkoff.academy.bookshelf.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import java.util.UUID;

@Getter
@Builder
@AllArgsConstructor
public class Depository {
    private UUID id;
    private String nick;
    private String address;
    private String description;
    private String type;
    private double latitude;
    private double longitude;
}
