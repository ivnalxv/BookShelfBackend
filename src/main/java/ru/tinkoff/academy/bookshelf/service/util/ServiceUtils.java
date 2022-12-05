package ru.tinkoff.academy.bookshelf.service.util;

import lombok.NonNull;
import ru.tinkoff.academy.bookshelf.domain.dao.Depository;
import ru.tinkoff.academy.bookshelf.domain.dto.BookDeposit;

public class ServiceUtils {
    private static double square(double a) {
        return a * a;
    }

    // For the simplicity I used not the real world sphere coordinates,
    // but simple euclidean distance(x, y) = sqrt((x_1 - x_2)^2 + (y_1 - y_2)^2)
    public static double calculateDistance(double latitude1,
                                           double longitude1,
                                           double latitude2,
                                           double longitude2) {
        return Math.sqrt(square(latitude1 - latitude2) + square(longitude1 - longitude2));
    }

    public static double calculateDistance(Depository depository,
                                           double latitude,
                                           double longitude) {
        return calculateDistance(
                depository.getLatitude(),
                depository.getLongitude(),
                latitude,
                longitude);
    }

    public static BookDeposit castToBookDeposit(@NonNull Depository depository) {
        return new BookDeposit(
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
