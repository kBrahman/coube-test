package kz.coube.delivery.domain.port;

import kz.coube.delivery.domain.model.PriceRequest;
import kz.coube.delivery.domain.model.PriceResult;

public interface CalculateDeliveryPriceUseCase {
    PriceResult calculate(PriceRequest request);
}
