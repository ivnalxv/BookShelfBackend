package ru.tinkoff.academy.bookshelf.mapper;

import org.springframework.stereotype.Component;
import ru.tinkoff.academy.bookshelf.entity.Depository;
import ru.tinkoff.academy.bookshelf.dto.BookDepositDto;

@Component
public class BookDepositDtoMapper implements DtoMapper<Depository, BookDepositDto> {
    public BookDepositDto entityToDto(Depository entity) {
        return BookDepositDto.builder()
                .id(entity.getId())
                .nick(entity.getNick())
                .address(entity.getAddress())
                .description(entity.getDescription())
                .type(entity.getType())
                .location(entity.getLatitude() + ", " + entity.getLongitude())
                .build();
    }

    public Depository dtoToEntity(BookDepositDto dto) {
        String[] location = dto.getLocation().split(",");
        return Depository.builder()
                .id(dto.getId())
                .nick(dto.getNick())
                .address(dto.getAddress())
                .description(dto.getDescription())
                .latitude(Double.parseDouble(location[0]))
                .longitude(Double.parseDouble(location[1]))
                .build();
    }
}
