package kz.coube.delivery.domain.model;

public record PriceRequest(
    double distanceKm,
    double weightTon,
    CargoType cargoType,
    boolean isUrgent
) {
}
