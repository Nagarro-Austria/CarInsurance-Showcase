package com.craftmanship.insurance.controller;

import com.craftmanship.insurance.model.CarInsuranceInputDTO;
import com.craftmanship.insurance.model.TaxRequest;
import com.craftmanship.insurance.service.LiabilityService;
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
    public BigDecimal calculateInsurance(
            @RequestBody TaxRequest input) {

        BigDecimal result = taxService.calculateTax(input);

        if (result == null)
            return BigDecimal.ZERO;

        return result;
    }
}
