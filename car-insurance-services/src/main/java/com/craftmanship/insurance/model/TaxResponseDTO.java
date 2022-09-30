package com.craftmanship.insurance.model;

import java.math.BigDecimal;
import java.util.Objects;

public final class TaxResponseDTO {
    private BigDecimal tax;

    public TaxResponseDTO() {
    }

    public TaxResponseDTO(BigDecimal tax) {
        this.tax = tax;
    }

    public BigDecimal getTax() {
        return tax;
    }

    public void setTax(BigDecimal tax) {
        this.tax = tax;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (TaxResponseDTO) obj;
        return Objects.equals(this.tax, that.tax);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tax);
    }

    @Override
    public String toString() {
        return "TaxResponseDTO[" +
                "tax=" + tax + ']';
    }

}
