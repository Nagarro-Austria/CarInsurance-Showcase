package com.craftmanship.insurance.model;

import java.math.BigDecimal;
import java.util.Objects;

public final class PremiumResponseDTO {
    private BigDecimal premium;

    public PremiumResponseDTO() {
    }

    public PremiumResponseDTO(BigDecimal premium) {
        this.premium = premium;
    }

    public BigDecimal getPremium() {
        return premium;
    }

    public void setPremium(BigDecimal premium) {
        this.premium = premium;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (PremiumResponseDTO) obj;
        return Objects.equals(this.premium, that.premium);
    }

    @Override
    public int hashCode() {
        return Objects.hash(premium);
    }

    @Override
    public String toString() {
        return "PremiumResponseDTO[" +
                "premium=" + premium + ']';
    }

}
