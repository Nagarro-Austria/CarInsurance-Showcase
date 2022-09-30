package com.craftmanship.insurance.service;

import com.craftmanship.insurance.entities.Coverage;
import com.craftmanship.insurance.model.PremiumRequestDTO;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Service
public class PremiumService {

    public BigDecimal calculatePremium(PremiumRequestDTO inputDTO, Coverage coverage) {
        BigDecimal premium = calculateBasisPremium(inputDTO.getPower(), coverage);
        premium = calculateBonusMalus(premium, inputDTO.getBonusMalus());
        premium = calculateZipCodeRisk(premium, inputDTO.getZipCode());

        premium = premium.setScale(2, RoundingMode.CEILING);

        return premium;
    }

    private BigDecimal calculateZipCodeRisk(BigDecimal premium, int zipCode) {
        if (zipCode >= 1000 && zipCode <= 3333) {
            return premium.multiply(BigDecimal.valueOf(0.95));
        } else if (zipCode >= 6667 && zipCode <= 9999) {
            return premium.multiply(BigDecimal.valueOf(1.05));
        }
        return premium;
    }

    private BigDecimal calculateBonusMalus(BigDecimal premium, int bonusMalus) {
        return premium.divide(BigDecimal.valueOf(100), 3, RoundingMode.UP)
                .multiply(BigDecimal.valueOf(calculateBonusMalus(bonusMalus)));
    }

    private BigDecimal calculateBasisPremium(int kilowatt, Coverage coverage) {
        if (kilowatt < 27) {
            return coverage.getMinPremium();
        } else if (kilowatt > 146) {
            return coverage.getMaxPremium();
        }
        return coverage.getPercentagePremium().multiply(BigDecimal.valueOf(kilowatt));
    }


    private int calculateBonusMalus(int stufe) {
        switch (stufe) {
            case 0:
            case 1:
                return 50;
            case 2:
            case 3:
                return 60;
            case 4:
            case 5:
                return 70;
            case 6:
            case 7:
                return 80;
            case 8:
            case 9:
                return 100;
            case 10:
            case 11:
                return 120;
            case 12:
            case 13:
                return 140;
            case 14:
            case 15:
                return 170;
            case 16:
            case 17:
                return 200;
        }
        return 100;
    }
}
