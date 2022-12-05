package ru.tinkoff.academy.bookshelf.domain.dao;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import java.util.UUID;

@Getter
@Setter
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
