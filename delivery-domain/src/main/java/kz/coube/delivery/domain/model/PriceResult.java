package kz.coube.delivery.domain.model;

import java.math.BigDecimal;

public record PriceResult(
    BigDecimal basePrice,
    BigDecimal urgentSurcharge,
    BigDecimal cargoTypeSurcharge,
    BigDecimal totalPrice,
    String currency
) {
}
