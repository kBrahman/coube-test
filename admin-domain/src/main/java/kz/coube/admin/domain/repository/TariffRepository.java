package kz.coube.admin.domain.repository;

import kz.coube.admin.domain.model.Tariff;

public interface TariffRepository {
    Tariff getLatestTariff();
    void updateTariff(Tariff tariff);
}
