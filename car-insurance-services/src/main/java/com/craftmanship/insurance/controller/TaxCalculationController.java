package com.craftmanship.insurance.controller;

import com.craftmanship.insurance.model.TaxRequestDTO;
import com.craftmanship.insurance.model.TaxResponseDTO;
import com.craftmanship.insurance.service.TaxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;

import static java.util.Arrays.asList;

@RestController
@RequestMapping("/tax")
public class TaxCalculationController {
    private static List<String> VALID_FUEL_TYPES = asList("hybrid", "gasoline", "diesel", "electricity");

    @Autowired
    private TaxService taxService;

    @PostMapping()
    public TaxResponseDTO calculateTax(@RequestBody TaxRequestDTO input) {

        if (input.getFuelType() == null || input.getPower() == null || input.getCo2Emissions() == null || input.getFirstRegistration() == null) {
            throw new InsuranceValidationException("All parameters for tax calculation are mandatory");
        }

        if (!VALID_FUEL_TYPES.contains(input.getFuelType())) {
            throw new InsuranceValidationException("Parameter fuel type '" + input.getFuelType() +"' is invalid");
        }

        BigDecimal result = taxService.calculateTax(input);

        if (result == null)
            return new TaxResponseDTO(BigDecimal.ZERO);

        return new TaxResponseDTO(result);
    }
}
