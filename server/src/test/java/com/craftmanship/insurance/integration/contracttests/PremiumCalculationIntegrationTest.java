package com.craftmanship.insurance.integration.contracttests;

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

    @Test
    public void calculatePremiumWithValidRESTContract() {
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
    public void calculatePremiumWithNullValuesShouldReturnPreconditionFailed(Integer power, Integer bonusMalus, Integer zipCode) {
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
