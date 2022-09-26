package com.craftmanship.insurance.service;

import com.craftmanship.insurance.model.CarInsuranceInputDTO;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Service
public class PremiumService {

    public BigDecimal calculatePremium(CarInsuranceInputDTO inputDTO) {
        BigDecimal premium = calculateBasisPremium(inputDTO.power());
        premium = calculateBonusMalus(premium, inputDTO.bonusMalus());
        premium = calculateZipCodeRisk(premium, inputDTO.zipCode());

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

    private BigDecimal calculateBasisPremium( int kilowatt) {
        if (kilowatt < 27) {
            return BigDecimal.valueOf(23.76);
        } else if (kilowatt > 146) {
            return BigDecimal.valueOf(132);
        }
        return BigDecimal.valueOf(kilowatt * 0.88);
    }

    private int calculateBonusMalus(int stufe){
        return switch (stufe) {
            case 0, 1 -> 50;
            case 2, 3 -> 60;
            case 4, 5 -> 70;
            case 6, 7 -> 80;
            case 8, 9 -> 100;
            case 10, 11 -> 120;
            case 12, 13 -> 140;
            case 14, 15 -> 170;
            case 16, 17 -> 200;
            default -> 100;
        };
    }
}
