package ru.tinkoff.academy.bookshelf.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import ru.tinkoff.academy.bookshelf.entity.Depository;
import ru.tinkoff.academy.bookshelf.dto.BookDepositDto;
import ru.tinkoff.academy.bookshelf.mapper.BookDepositDtoMapper;
import ru.tinkoff.academy.bookshelf.utils.DepositoryServiceUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;


@Service
@AllArgsConstructor
public class DepositoryService {
    private BookDepositDtoMapper bookDepositDtoMapper;

    private final List<Depository> depositories = new ArrayList<>(){
        {
            Depository.DepositoryBuilder builder = Depository.builder();
            add(builder.id(UUID.randomUUID()).nick("1").latitude(0).longitude(0).build());
            add(builder.id(UUID.randomUUID()).nick("2").latitude(1).longitude(0).build());
            add(builder.id(UUID.randomUUID()).nick("3").latitude(2).longitude(0).build());
            add(builder.id(UUID.randomUUID()).nick("4").latitude(0).longitude(2).build());
        }
    };

    public Flux<BookDepositDto> getNearestBookDeposits(
            UUID id,
            long distance,
            long amount
    ) {
        Depository depository = getDepositoryById(id);
        if (Objects.isNull(depository)) {
            return Flux.just();
        }

        return getNearestBookDeposits(
                depository.getLatitude(),
                depository.getLongitude(),
                distance,
                amount);
    }

    public Flux<BookDepositDto> getNearestBookDeposits(
            double latitude,
            double longitude,
            long distance,
            long amount
    ) {
        sortingDepositoriesByDistance(latitude, longitude);
        return Flux.just(getNearestFromSortedDepositories(
                latitude,
                longitude,
                distance,
                amount
        ).toArray(new BookDepositDto[0]));
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
        Depository depository = Depository.builder()
                .id(id)
                .nick(nick)
                .address(address)
                .description(description)
                .type(type)
                .latitude(latitude)
                .longitude(longitude)
                .build();
        depositories.add(depository);
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

    private void sortingDepositoriesByDistance(double latitude, double longitude) {
        depositories.sort((o1, o2) -> {
            double dist1 = DepositoryServiceUtils.calculateDistance(o1, latitude, longitude);
            double dist2 = DepositoryServiceUtils.calculateDistance(o2, latitude, longitude);
            return Double.compare(dist1, dist2);
        });
    }

    private List<BookDepositDto> getNearestFromSortedDepositories(
            double latitude,
            double longitude,
            long distance,
            long amount
    ) {
        ArrayList<BookDepositDto> nearestBookDeposits = new ArrayList<>();
        for (int i = 0; i < depositories.size() && i < amount; i++) {
            Depository d = depositories.get(i);
            if (DepositoryServiceUtils.calculateDistance(d, latitude, longitude) > distance) {
                break;
            }
            nearestBookDeposits.add(bookDepositDtoMapper.entityToDto(d));
        }
        return nearestBookDeposits;
    }

    private boolean checkIfExistsById(UUID id) {
        return !Objects.isNull(getDepositoryById(id));
    }
}
