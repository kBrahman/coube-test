package kz.coube.delivery.domain.usecase;

import kz.coube.delivery.domain.model.CargoType;
import kz.coube.delivery.domain.model.PriceRequest;
import kz.coube.delivery.domain.model.PriceResult;
import kz.coube.delivery.domain.model.Tariff;
import kz.coube.delivery.domain.repository.TariffRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class CalculateDeliveryPriceKZTest {

    private CalculateDeliveryPriceKZ useCase;

    @BeforeEach
    void setUp() {
        TariffRepository mockRepository = new TariffRepository() {
            @Override
            public Tariff getLatestTariff() {
                return new Tariff(
                        BigDecimal.valueOf(8),
                        BigDecimal.valueOf(0.20),
                        BigDecimal.valueOf(0.10),
                        BigDecimal.valueOf(0.25));
            }

        };
        useCase = new CalculateDeliveryPriceKZ(mockRepository);
    }

    @Test
    void shouldCalculateCorrectlyForFragileAndUrgent() {
        PriceRequest request = new PriceRequest(450, 12.5, CargoType.FRAGILE, true);
        PriceResult result = useCase.calculate(request);
        assertEquals(new BigDecimal("45000.00"), result.basePrice());
        assertEquals(new BigDecimal("9000.00"), result.urgentSurcharge());
        assertEquals(new BigDecimal("4500.00"), result.cargoTypeSurcharge());
        assertEquals(new BigDecimal("58500.00"), result.totalPrice());
        assertEquals("KZT", result.currency());
    }

    @Test
    void shouldCalculateCorrectlyForStandardNonUrgent() {
        PriceRequest request = new PriceRequest(100, 10, CargoType.STANDARD, false);
        PriceResult result = useCase.calculate(request);
        assertEquals(new BigDecimal("8000.00"), result.basePrice());
        assertEquals(new BigDecimal("0.00"), result.urgentSurcharge());
        assertEquals(new BigDecimal("0.00"), result.cargoTypeSurcharge());
        assertEquals(new BigDecimal("8000.00"), result.totalPrice());
    }

    @Test
    void shouldThrowExceptionForInvalidDistance() {
        PriceRequest request = new PriceRequest(6000, 10, CargoType.STANDARD, false);
        assertThrows(IllegalArgumentException.class, () -> useCase.calculate(request));
    }

    @Test
    void shouldThrowExceptionForInvalidWeight() {
        PriceRequest request = new PriceRequest(100, 0, CargoType.STANDARD, false);
        assertThrows(IllegalArgumentException.class, () -> useCase.calculate(request));
    }
}
