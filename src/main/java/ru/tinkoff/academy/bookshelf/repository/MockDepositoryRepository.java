package ru.tinkoff.academy.bookshelf.repository;

import org.springframework.stereotype.Repository;
import ru.tinkoff.academy.bookshelf.entity.Depository;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

@Repository
public class MockDepositoryRepository implements DepositoryRepository {
    private final Random random = new Random();
    private final ArrayList<Depository> depositories = new ArrayList<>();

    public MockDepositoryRepository() {
        for (int i = 0; i < 10; i++) {
            Depository depository = Depository.builder()
                    .id(UUID.randomUUID())
                    .nick(generateRandomAlphabeticString(10))
                    .longitude(random.nextInt(10))
                    .latitude(random.nextInt(10))
                    .build();
            depositories.add(depository);
        }
    }

    private String generateRandomAlphabeticString(int size) {
        return random.ints('a', 'z' + 1)
                .limit(size)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
    }

    public List<Depository> findAll() {
        return depositories;
    }
}
