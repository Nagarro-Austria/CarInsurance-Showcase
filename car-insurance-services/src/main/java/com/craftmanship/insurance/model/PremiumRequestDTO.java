package com.craftmanship.insurance.model;

import java.util.Objects;

public final class PremiumRequestDTO {
    private final Integer power;
    private final Integer bonusMalus;
    private final Integer zipCode;
    private final Long coverageId;

    public PremiumRequestDTO(Integer power, Integer bonusMalus, Integer zipCode, Long coverageId) {
        this.power = power;
        this.bonusMalus = bonusMalus;
        this.zipCode = zipCode;
        this.coverageId = coverageId;
    }

    public Integer getPower() {
        return power;
    }

    public Integer getBonusMalus() {
        return bonusMalus;
    }

    public Integer getZipCode() {
        return zipCode;
    }

    public Long getCoverageId() {
        return coverageId;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (PremiumRequestDTO) obj;
        return Objects.equals(this.power, that.power) &&
                Objects.equals(this.bonusMalus, that.bonusMalus) &&
                Objects.equals(this.zipCode, that.zipCode) &&
                Objects.equals(this.coverageId, that.coverageId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(power, bonusMalus, zipCode, coverageId);
    }

    @Override
    public String toString() {
        return "PremiumRequestDTO[" +
                "power=" + power + ", " +
                "bonusMalus=" + bonusMalus + ", " +
                "zipCode=" + zipCode + ", " +
                "coverageId=" + coverageId + ']';
    }

}
