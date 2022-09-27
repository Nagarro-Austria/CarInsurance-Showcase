package com.craftmanship.insurance.model;

import java.time.LocalDate;

public record TaxRequestDTO(int co2Emmisions, int power, String fuelType, LocalDate firstRegistration) {
}
