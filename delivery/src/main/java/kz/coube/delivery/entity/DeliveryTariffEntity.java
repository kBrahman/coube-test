package kz.coube.delivery.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.math.BigDecimal;

@Entity
@Table(name = "tariffs")
public class DeliveryTariffEntity {

    @Id
    private Long id = 1L;

    private BigDecimal baseRateModifier;
    private BigDecimal urgentSurchargePercentage;
    private BigDecimal fragileSurchargePercentage;
    private BigDecimal oversizedSurchargePercentage;

    public DeliveryTariffEntity() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getBaseRateModifier() {
        return baseRateModifier;
    }

    public void setBaseRateModifier(BigDecimal baseRateModifier) {
        this.baseRateModifier = baseRateModifier;
    }

    public BigDecimal getUrgentSurchargePercentage() {
        return urgentSurchargePercentage;
    }

    public void setUrgentSurchargePercentage(BigDecimal urgentSurchargePercentage) {
        this.urgentSurchargePercentage = urgentSurchargePercentage;
    }

    public BigDecimal getFragileSurchargePercentage() {
        return fragileSurchargePercentage;
    }

    public void setFragileSurchargePercentage(BigDecimal fragileSurchargePercentage) {
        this.fragileSurchargePercentage = fragileSurchargePercentage;
    }

    public BigDecimal getOversizedSurchargePercentage() {
        return oversizedSurchargePercentage;
    }

    public void setOversizedSurchargePercentage(BigDecimal oversizedSurchargePercentage) {
        this.oversizedSurchargePercentage = oversizedSurchargePercentage;
    }
}
