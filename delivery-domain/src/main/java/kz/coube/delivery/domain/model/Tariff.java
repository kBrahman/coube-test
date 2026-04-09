package kz.coube.delivery.domain.model;

import java.math.BigDecimal;

public record Tariff(
    BigDecimal baseRateModifier,
    BigDecimal urgentSurchargePercentage,
    BigDecimal fragileSurchargePercentage,
    BigDecimal oversizedSurchargePercentage
) {
}
