package ru.tinkoff.academy.bookshelf.service.util;

import lombok.NonNull;
import ru.tinkoff.academy.bookshelf.dao.Depository;
import ru.tinkoff.academy.bookshelf.dto.BookDepositDto;

public class ObjectMapperUtils {
    public static BookDepositDto fromDepositoryToBookDepositDto(@NonNull Depository depository) {
        return new BookDepositDto(
                depository.getId(),
                depository.getNick(),
                depository.getAddress(),
                depository.getDescription(),
                depository.getType(),
                depository.getLatitude()
                        + ", " + depository.getLongitude()
        );
    }
}
