package com.craftmanship.insurance.model;

import java.time.LocalDate;

public record TaxRequestDTO(int co2Emissions, int power, String fuelType, LocalDate firstRegistration) {
}
