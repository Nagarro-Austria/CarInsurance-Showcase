package com.craftmanship.insurance.model;

import java.time.LocalDate;

public record TaxRequest (int co2, int kw, String fuel, LocalDate registrationDate ) {
}
