package com.craftmanship.insurance.integration.logictests;

import com.craftmanship.insurance.model.PremiumRequestDTO;
import com.craftmanship.insurance.model.PremiumResponseDTO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.math.BigDecimal;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;

public class PremiumCalculationIntegrationTest {
    public static final int NO_RISK_ZIP_CODE = 4000;
    public static final int STANDARD_POWER = 100;
    public static final int BONUS_MALUS_LEVEL = 9;
    private int port = 8080;

    @ParameterizedTest
    @CsvSource({" 0, 44.00",
            " 1, 44.00",
            " 2, 52.80",
            " 3, 52.80",
            " 4, 61.60",
            " 5, 61.60",
            " 6, 70.40",
            " 8, 88.00",
            " 9, 88.00",
            " 10, 105.60",
            " 11, 105.60",
            " 12, 123.20",
            " 13, 123.20",
            " 14, 149.60",
            " 15, 149.60",
            " 16, 176.00",
            " 17, 176.00"})
    public void calculatePremiumWithDifferentBonusMalusLevels(int bonusMalusLevel, String expectedPremium) {
        PremiumRequestDTO input = new PremiumRequestDTO(STANDARD_POWER, bonusMalusLevel, NO_RISK_ZIP_CODE,3L);
        BigDecimal result =
                given()
                        .contentType("application/json")
                        .body(input)
                        .when()
                        .post(createURLWithPort("/premium"))
                        .as(PremiumResponseDTO.class).premium();

        assertThat(result).isEqualTo(new BigDecimal(expectedPremium));
    }

    @ParameterizedTest
    @CsvSource({"10, 23.80",
            "27, 23.80",
            "28, 24.70",
            "146, 128.50",
            "147, 132.00",
            "200, 132.00"})
    public void calculatePremiumWithDifferentPowerRanges(int power, String expectedPremium) {
        PremiumRequestDTO input = new PremiumRequestDTO(power, 9, NO_RISK_ZIP_CODE,3L);

        BigDecimal result =
                given()
                        .contentType("application/json")
                        .body(input)
                        .when()
                        .post(createURLWithPort("/premium"))
                        .as(PremiumResponseDTO.class).premium();

        assertThat(result).isEqualTo(new BigDecimal(expectedPremium));
    }

    @ParameterizedTest
    @CsvSource({"1000, 83.60",
            "3333, 83.60",
            "3334, 88.00",
            "6666, 88.00",
            "6667, 92.40",
            "9999, 92.40"})
    public void calculatePremiumWithDifferentRiskLocations(int zipCode, String expectedPremium) {
        PremiumRequestDTO input = new PremiumRequestDTO(STANDARD_POWER, BONUS_MALUS_LEVEL, zipCode,3L);

        BigDecimal result =
                given()
                        .contentType("application/json")
                        .body(input)
                        .when()
                        .post(createURLWithPort("/premium"))
                        .as(PremiumResponseDTO.class).premium();

        assertThat(result).isEqualTo(new BigDecimal(expectedPremium));
    }

    @Test
    public void calculatePremiumWithValidContract() {
        PremiumRequestDTO input = new PremiumRequestDTO(100, 9, 4000,3L);

        var result = given()
                .contentType("application/json")
                .body(input)
                .when()
                .post(createURLWithPort("/premium"))
                .as(PremiumResponseDTO.class).premium();

        assertThat(result).isEqualTo(new BigDecimal("88.00"));
    }

    @ParameterizedTest
    @CsvSource(
            value = {"null, 9, 1000",
                    "100, null, 1000",
                    "100, 9, null"},
            nullValues = {"null"}
    )
    public void calculatePremiumWithInvalidParamsShouldReturnPreconditionFailed(Integer power, Integer bonusMalus, Integer zipCode) {
        PremiumRequestDTO input = new PremiumRequestDTO(power, bonusMalus, zipCode,3L);

        var result = given()
                .contentType("application/json")
                .body(input)
                .when()
                .post(createURLWithPort("/premium"));

        assertThat(result.statusCode()).isEqualTo(412);
    }

    private String createURLWithPort(String uri) {
        return "http://localhost:" + port + uri;
    }
}
