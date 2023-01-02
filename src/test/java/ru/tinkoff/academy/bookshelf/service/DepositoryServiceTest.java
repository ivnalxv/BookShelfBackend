package ru.tinkoff.academy.bookshelf.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.tinkoff.academy.bookshelf.entity.Depository;

import java.util.List;
import java.util.Objects;
import java.util.Random;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

@SpringBootTest
public class DepositoryServiceTest {
    @Autowired
    private DepositoryService depositoryService;
    private final Random random = new Random();

    private Depository getDepositoryById(UUID id) {
        return depositoryService.getDepositoryById(id);
    }

    private Depository takeRandomDepository() {
        List<Depository> depositories = depositoryService.getDepositories();
        return depositories.get(random.nextInt(depositories.size()));
    }

    public static Depository generateRandomDepository() {
        return Depository.builder()
                .id(UUID.randomUUID())
                .longitude(1)
                .latitude(1)
                .build();
    }

    private boolean checkIfExistsById(UUID id) {
        if (Objects.isNull(id)) {
            return false;
        }
        return !Objects.isNull(getDepositoryById(id));
    }

    @Test
    public void getDepositoryByIdTest() {
        // given
        Depository guessDepository = takeRandomDepository();

        // when
        Depository presumedDepository = getDepositoryById(guessDepository.getId());

        // then
        assertEquals(guessDepository, presumedDepository);
    }

    @Test
    public void createDepositoryTest() {
        // given
        Depository createdDepository = generateRandomDepository();

        // when
        depositoryService.createDepository(
                createdDepository.getId(),
                createdDepository.getNick(),
                createdDepository.getAddress(),
                createdDepository.getDescription(),
                createdDepository.getType(),
                createdDepository.getLatitude(),
                createdDepository.getLongitude());

        // then
        assertTrue(checkIfExistsById(createdDepository.getId()));
    }

    @Test
    public void updateDepositoryTest() {
        // given
        Depository updatedDepository = takeRandomDepository();

        // when
        Depository.DepositoryBuilder builder = Depository.builder()
                .id(updatedDepository.getId())
                .nick(updatedDepository.getNick())
                .address(updatedDepository.getAddress())
                .description(updatedDepository.getDescription())
                .type(updatedDepository.getType())
                .latitude(updatedDepository.getLatitude())
                .longitude(updatedDepository.getLongitude());

        Depository clonedDepository = builder.build();

        updatedDepository = builder.nick("0123456789").build();
        depositoryService.updateDepository(clonedDepository.getId(), updatedDepository);

        // then
        assertNotEquals(
                clonedDepository,
                getDepositoryById(clonedDepository.getId())
        );
    }

    @Test
    public void deleteDepositoryTest() {
        // given
        Depository takenDepository = takeRandomDepository();

        // when
        UUID id = takenDepository.getId();

        // then
        depositoryService.deleteDepository(id);
        assertFalse(checkIfExistsById(id));
    }
}
