package kz.coube.delivery.config;

import kz.coube.delivery.domain.port.CalculateDeliveryPriceUseCase;
import kz.coube.delivery.domain.repository.TariffRepository;
import kz.coube.delivery.domain.usecase.CalculateDeliveryPriceKZ;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DeliveryConfig {

    @Bean
    public CalculateDeliveryPriceUseCase calculateDeliveryPriceUseCase(TariffRepository tariffRepository) {
        return new CalculateDeliveryPriceKZ(tariffRepository);
    }
}

