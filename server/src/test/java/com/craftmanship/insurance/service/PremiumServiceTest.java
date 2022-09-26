package com.craftmanship.insurance.service;

import com.craftmanship.insurance.model.CarInsuranceInputDTO;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.math.BigDecimal;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.*;

class PremiumServiceTest {

    @ParameterizedTest
    @CsvSource({"100, 0, 4000, 44.00",
            "100, 1, 4000, 44.00",
            "100, 2, 4000, 52.80",
            "100, 3, 4000, 52.80",
            "100, 4, 4000, 61.60",
            "100, 5, 4000, 61.60",
            "100, 6, 4000, 70.40",
            "100, 8, 4000, 88.00",
            "100, 9, 4000, 88.00",
            "100, 10, 4000, 105.60",
            "100, 11, 4000, 105.60",
            "100, 12, 4000, 123.20",
            "100, 13, 4000, 123.20",
            "100, 14, 4000, 149.60",
            "100, 15, 4000, 149.60",
            "100, 16, 4000, 176.00",
            "100, 17, 4000, 176.00"})
    public void calculatePremiumWithDifferentBonusMalusLevels(String power, String bonusMalusLevel, String zipCode, String expectedPremium) {
        CarInsuranceInputDTO input = new CarInsuranceInputDTO(Integer.valueOf(power), Integer.valueOf(bonusMalusLevel), Integer.valueOf(zipCode));

        assertEquals(new PremiumService().calculatePremium(input), new BigDecimal(expectedPremium));
    }
}