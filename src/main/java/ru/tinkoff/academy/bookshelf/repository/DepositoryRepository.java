package ru.tinkoff.academy.bookshelf.repository;

import ru.tinkoff.academy.bookshelf.entity.Depository;

import java.util.List;

public interface DepositoryRepository {
    List<Depository> findAll();
}
