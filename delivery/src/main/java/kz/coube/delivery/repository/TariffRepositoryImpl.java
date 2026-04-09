package kz.coube.delivery.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import kz.coube.delivery.domain.model.Tariff;
import kz.coube.delivery.domain.repository.TariffRepository;
import kz.coube.delivery.entity.DeliveryTariffEntity;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Optional;

@Repository
public class TariffRepositoryImpl implements TariffRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional(readOnly = true)
    public Tariff getLatestTariff() {
        DeliveryTariffEntity entity = entityManager.find(DeliveryTariffEntity.class, 1L);
        return Optional.ofNullable(entity)
                .map(e -> new Tariff(
                        e.getBaseRateModifier(),
                        e.getUrgentSurchargePercentage(),
                        e.getFragileSurchargePercentage(),
                        e.getOversizedSurchargePercentage()))
                .orElseGet(() -> new Tariff(
                        BigDecimal.valueOf(8),
                        BigDecimal.valueOf(0.20),
                        BigDecimal.valueOf(0.10),
                        BigDecimal.valueOf(0.25)));
    }
}
