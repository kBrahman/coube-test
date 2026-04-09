package kz.coube.delivery.domain.usecase;

import kz.coube.delivery.domain.model.PriceRequest;
import kz.coube.delivery.domain.model.PriceResult;
import kz.coube.delivery.domain.model.Tariff;
import kz.coube.delivery.domain.port.CalculateDeliveryPriceUseCase;
import kz.coube.delivery.domain.repository.TariffRepository;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class CalculateDeliveryPriceKZ implements CalculateDeliveryPriceUseCase {

    private final TariffRepository tariffRepository;

    public CalculateDeliveryPriceKZ(TariffRepository tariffRepository) {
        this.tariffRepository = tariffRepository;
    }

    @Override
    public PriceResult calculate(PriceRequest request) {
        validate(request);

        Tariff tariff = tariffRepository.getLatestTariff();

        BigDecimal basePrice = BigDecimal.valueOf(request.distanceKm())
                .multiply(BigDecimal.valueOf(request.weightTon()))
                .multiply(tariff.baseRateModifier());

        BigDecimal urgentSurcharge = BigDecimal.ZERO;
        if (request.isUrgent()) {
            urgentSurcharge = basePrice.multiply(tariff.urgentSurchargePercentage());
        }

        BigDecimal cargoTypeSurcharge = switch (request.cargoType()) {
            case FRAGILE -> basePrice.multiply(tariff.fragileSurchargePercentage());
            case OVERSIZED -> basePrice.multiply(tariff.oversizedSurchargePercentage());
            case STANDARD -> BigDecimal.ZERO;
        };

        BigDecimal totalPrice = basePrice.add(urgentSurcharge).add(cargoTypeSurcharge);

        return new PriceResult(
                basePrice.setScale(2, RoundingMode.HALF_UP),
                urgentSurcharge.setScale(2, RoundingMode.HALF_UP),
                cargoTypeSurcharge.setScale(2, RoundingMode.HALF_UP),
                totalPrice.setScale(2, RoundingMode.HALF_UP),
                "KZT");
    }

    private void validate(PriceRequest request) {
        if (request.distanceKm() < 1 || request.distanceKm() > 5000) {
            throw new IllegalArgumentException("Distance must be between 1 and 5000 km");
        }
        if (request.weightTon() < 0.1 || request.weightTon() > 120) {
            throw new IllegalArgumentException("Weight must be between 0.1 and 120 tons");
        }
        if (request.cargoType() == null) {
            throw new IllegalArgumentException("Cargo type is required");
        }
    }

}
