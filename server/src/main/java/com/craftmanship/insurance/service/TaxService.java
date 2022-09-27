package com.craftmanship.insurance.service;

import com.craftmanship.insurance.model.TaxRequestDTO;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;

@Service
public class TaxService {
    public BigDecimal calculateTax(TaxRequestDTO input) {
        BigDecimal tax = new BigDecimal(0);
        if (input.firstRegistration().isAfter(LocalDate.of(2020, 10, 1))) {
            tax = calculateAfter(input);
        }
        return null;
    }

    private BigDecimal calculateAfter(TaxRequestDTO input) {
        return null;
    }
}
