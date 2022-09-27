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

@RestController
@RequestMapping("/tax")
public class TaxCalculationController {
    @Autowired
    private TaxService taxService;

    @PostMapping()
    public TaxResponseDTO calculateTax(@RequestBody TaxRequestDTO input) {

        BigDecimal result = taxService.calculateTax(input);

        if (result == null)
            return new TaxResponseDTO(BigDecimal.ZERO);

        return new TaxResponseDTO(result);
    }
}
