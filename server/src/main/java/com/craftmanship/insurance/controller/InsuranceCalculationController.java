package com.craftmanship.insurance.controller;

import com.craftmanship.insurance.model.CarInsuranceInputDTO;
import com.craftmanship.insurance.service.LiabilityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
@RequestMapping("/insurance")
public class InsuranceCalculationController {
    @Autowired
    private LiabilityService liabilityService;

    @PostMapping()
    public BigDecimal calculateInsurance(
            @RequestBody CarInsuranceInputDTO input) {

        BigDecimal result = liabilityService.calculateLiability(input);

        if (result == null)
            return BigDecimal.ZERO;

        return result;
    }
}
