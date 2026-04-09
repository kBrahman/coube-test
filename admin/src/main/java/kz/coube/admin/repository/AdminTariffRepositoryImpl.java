package kz.coube.admin.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import kz.coube.admin.domain.model.Tariff;
import kz.coube.admin.domain.repository.TariffRepository;
import kz.coube.admin.entity.AdminTariffEntity;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Optional;

@Repository
public class AdminTariffRepositoryImpl implements TariffRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional(readOnly = true)
    public Tariff getLatestTariff() {
        AdminTariffEntity entity = entityManager.find(AdminTariffEntity.class, 1L);
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

    @Override
    @Transactional
    public void updateTariff(Tariff tariff) {
        AdminTariffEntity entity = new AdminTariffEntity(
                1L,
                tariff.baseRateModifier(),
                tariff.urgentSurchargePercentage(),
                tariff.fragileSurchargePercentage(),
                tariff.oversizedSurchargePercentage());
        entityManager.merge(entity);
    }
}
