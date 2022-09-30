package com.craftmanship.insurance.model;

import java.time.LocalDate;
import java.util.Objects;

public final class TaxRequestDTO {
    private final Integer co2Emissions;
    private final Integer power;
    private final String fuelType;
    private final LocalDate firstRegistration;

    public TaxRequestDTO(Integer co2Emissions, Integer power, String fuelType, LocalDate firstRegistration) {
        this.co2Emissions = co2Emissions;
        this.power = power;
        this.fuelType = fuelType;
        this.firstRegistration = firstRegistration;
    }

    public Integer getCo2Emissions() {
        return co2Emissions;
    }

    public Integer getPower() {
        return power;
    }

    public String getFuelType() {
        return fuelType;
    }

    public LocalDate getFirstRegistration() {
        return firstRegistration;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (TaxRequestDTO) obj;
        return Objects.equals(this.co2Emissions, that.co2Emissions) &&
                Objects.equals(this.power, that.power) &&
                Objects.equals(this.fuelType, that.fuelType) &&
                Objects.equals(this.firstRegistration, that.firstRegistration);
    }

    @Override
    public int hashCode() {
        return Objects.hash(co2Emissions, power, fuelType, firstRegistration);
    }

    @Override
    public String toString() {
        return "TaxRequestDTO[" +
                "co2Emissions=" + co2Emissions + ", " +
                "power=" + power + ", " +
                "fuelType=" + fuelType + ", " +
                "firstRegistration=" + firstRegistration + ']';
    }

}
