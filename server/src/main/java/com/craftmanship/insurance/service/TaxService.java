package com.craftmanship.insurance.service;

import com.craftmanship.insurance.model.TaxRequest;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;

@Service
public class TaxService {
    public BigDecimal calculateTax(TaxRequest input) {
        BigDecimal tax = new BigDecimal(0);
        if (input.registrationDate().isAfter(LocalDate.of(2020, 10, 1))) {
            tax = calculateAfter(input);
        }
        return null;
    }

    private BigDecimal calculateAfter(TaxRequest input) {
        return null;
    }
}
