package com.craftmanship.insurance.controller;

import com.craftmanship.insurance.model.PremiumRequestDTO;
import com.craftmanship.insurance.model.PremiumResponseDTO;
import com.craftmanship.insurance.service.PremiumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
@RequestMapping("/premium")
public class PremiumCalculationController {
    @Autowired
    private PremiumService liabilityService;

    @PostMapping()
    public PremiumResponseDTO calculatePremium(@RequestBody PremiumRequestDTO input) {

        BigDecimal result = liabilityService.calculatePremium(input);

        if (result == null)
            return new PremiumResponseDTO(BigDecimal.ZERO);

        return new PremiumResponseDTO(result);
    }
}
