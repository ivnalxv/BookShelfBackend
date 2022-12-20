package ru.tinkoff.academy.bookshelf.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.tinkoff.academy.bookshelf.domain.dao.Depository;
import ru.tinkoff.academy.bookshelf.service.util.ServiceUtils;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

@SpringBootTest
public class DepositoryServiceTest {
    @Autowired
    private DepositoryService depositoryService;


    private Depository getDepositoryById(UUID id) {
        return depositoryService.getDepositoryById(id);
    }
    private Depository takeRandomDepository() {
        List<Depository> depositories = depositoryService.getDepositories();
        return depositories.get(ServiceUtils.randomInt(depositories.size()));
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
        Depository createdDepository = ServiceUtils.generateRandomDepository();

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
        Depository clonedDepository = new Depository(
                updatedDepository.getId(),
                updatedDepository.getNick(),
                updatedDepository.getAddress(),
                updatedDepository.getDescription(),
                updatedDepository.getType(),
                updatedDepository.getLatitude(),
                updatedDepository.getLongitude());
        updatedDepository.setNick(ServiceUtils.generateRandomAlphabeticString(9));
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
