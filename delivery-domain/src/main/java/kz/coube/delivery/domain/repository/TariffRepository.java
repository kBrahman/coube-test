package kz.coube.delivery.domain.repository;

import kz.coube.delivery.domain.model.Tariff;

public interface TariffRepository {
    Tariff getLatestTariff();
}
