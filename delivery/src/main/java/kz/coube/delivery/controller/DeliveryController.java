package kz.coube.delivery.controller;

import kz.coube.delivery.domain.model.PriceRequest;
import kz.coube.delivery.domain.model.PriceResult;
import kz.coube.delivery.domain.port.CalculateDeliveryPriceUseCase;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/delivery")
public class DeliveryController {

    private final CalculateDeliveryPriceUseCase calculateDeliveryPriceUseCase;

    public DeliveryController(CalculateDeliveryPriceUseCase calculateDeliveryPriceUseCase) {
        this.calculateDeliveryPriceUseCase = calculateDeliveryPriceUseCase;
    }

    @PostMapping("/calculate")
    public ResponseEntity<PriceResult> calculate(@RequestBody PriceRequest request) {
        try {
            PriceResult result = calculateDeliveryPriceUseCase.calculate(request);
            return ResponseEntity.ok(result);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
