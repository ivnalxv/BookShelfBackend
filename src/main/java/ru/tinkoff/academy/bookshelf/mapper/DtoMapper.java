package ru.tinkoff.academy.bookshelf.mapper;

import lombok.NonNull;

public interface DtoMapper<E, D> {
    D entityToDto(@NonNull E entity);

    @SuppressWarnings("unused")
    E dtoToEntity(@NonNull D dto);
}
