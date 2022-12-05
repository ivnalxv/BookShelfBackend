package ru.tinkoff.academy.bookshelf.service;

import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import ru.tinkoff.academy.bookshelf.domain.dao.Depository;
import ru.tinkoff.academy.bookshelf.domain.dto.BookDeposit;
import ru.tinkoff.academy.bookshelf.service.util.ServiceUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;


@Service
public class DepositoryService {
    private final List<Depository> depositories = new ArrayList<>(){
        {
            add(new Depository(UUID. randomUUID(), "1", "", "", "", 0, 0));
            add(new Depository(UUID. randomUUID(), "2", "", "", "", 1, 0));
            add(new Depository(UUID. randomUUID(), "3", "", "", "", 2, 0));
            add(new Depository(UUID. randomUUID(), "4", "", "", "", 0, 2));
        }
    };

    public Flux<BookDeposit> getNearestBookDeposits(
            double latitude,
            double longitude,
            long distance,
            long amount
    ) {
        depositories.sort((o1, o2) -> {
            double dist1 = ServiceUtils.calculateDistance(o1, latitude, longitude);
            double dist2 = ServiceUtils.calculateDistance(o2, latitude, longitude);
            return Double.compare(dist1, dist2);
        });

        System.out.println(depositories);

        ArrayList<BookDeposit> nearestBookDeposits = new ArrayList<>();
        for (int i = 0; i < depositories.size() && i < amount; i++) {
            Depository d = depositories.get(i);
            if (ServiceUtils.calculateDistance(d, latitude, longitude) > distance) {
                break;
            }
            nearestBookDeposits.add(ServiceUtils.castToBookDeposit(d));
        }

        return Flux.just(nearestBookDeposits.toArray(new BookDeposit[0]));
    }

    public List<Depository> getDepositories() {
        return depositories;
    }

    public Depository getDepositoryById(UUID id) {
        return getDepositories().stream()
                .filter(d -> d.getId().equals(id))
                .findAny()
                .orElse(null);
    }

    @SuppressWarnings("unused")
    public void createDepository(
            UUID id,
            String nick,
            String address,
            String description,
            String type,
            double latitude,
            double longitude
    ) {
        if (checkIfExistsById(id)) {
            return;
        }
        depositories.add(new Depository(id, nick, address, description, type, latitude, longitude));
    }

    @SuppressWarnings("unused")
    public void updateDepository(UUID id, Depository depository) {
        if (!checkIfExistsById(id)) {
            return;
        }
        depositories.set(depositories.indexOf(getDepositoryById(id)), depository);
    }

    @SuppressWarnings("unused")
    public void deleteDepository(UUID id) {
        if (!checkIfExistsById(id)) {
            return;
        }
        depositories.remove(getDepositoryById(id));
    }

    private boolean checkIfExistsById(UUID id) {
        return !Objects.isNull(getDepositoryById(id));
    }
}
