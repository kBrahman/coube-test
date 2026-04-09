package kz.coube.admin.config;

import kz.coube.admin.domain.repository.TariffRepository;
import kz.coube.admin.domain.usecase.UpdateTariffUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AdminConfig {

    @Bean
    public UpdateTariffUseCase updateTariffUseCase(TariffRepository tariffRepository) {
        return new UpdateTariffUseCase(tariffRepository);
    }
}
