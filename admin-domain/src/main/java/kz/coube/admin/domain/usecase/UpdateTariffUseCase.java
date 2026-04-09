package kz.coube.admin.domain.usecase;

import kz.coube.admin.domain.model.Tariff;
import kz.coube.admin.domain.repository.TariffRepository;

public class UpdateTariffUseCase {

    private final TariffRepository tariffRepository;

    public UpdateTariffUseCase(TariffRepository tariffRepository) {
        this.tariffRepository = tariffRepository;
    }

    public void updateTariff(Tariff tariff) {
        if (tariff.baseRateModifier() == null || tariff.urgentSurchargePercentage() == null
                || tariff.fragileSurchargePercentage() == null || tariff.oversizedSurchargePercentage() == null) {
            throw new IllegalArgumentException("Tariff values cannot be null");
        }
        tariffRepository.updateTariff(tariff);
    }
}
