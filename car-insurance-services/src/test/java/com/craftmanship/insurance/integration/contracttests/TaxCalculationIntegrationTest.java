package com.craftmanship.insurance.integration.contracttests;

import com.craftmanship.insurance.model.TaxRequestDTO;
import com.craftmanship.insurance.model.TaxResponseDTO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.math.BigDecimal;
import java.time.LocalDate;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;

public class TaxCalculationIntegrationTest {
    private int port = 8080;

    private BigDecimal callService(TaxRequestDTO input) {
        var result = given()
                .contentType("application/json")
                .body(input)
                .when()
                .post(createURLWithPort("/tax"))
                .as(TaxResponseDTO.class).getTax();

        return result;
    }

    @Test
    public void calculateTaxWithValidRESTContract() {

        TaxRequestDTO input = new TaxRequestDTO(100, 100, "diesel", LocalDate.of(2022, 4, 21));

        assertThat(callService(input)).isEqualTo(new BigDecimal("28.80"));
    }

    @ParameterizedTest
    @CsvSource(
            value = {"null, 100, hybrid",
                    "100, null, hybrid",
                    "100, 100, null"},
            nullValues = {"null"}
    )
    public void calculateTaxWithNullValuesShouldReturnPreconditionFailed(Integer co2Emissions, Integer power, String fuelType) {
        TaxRequestDTO input = new TaxRequestDTO(co2Emissions, power, fuelType, LocalDate.now());

        var result = given()
                .contentType("application/json")
                .body(input)
                .when()
                .post(createURLWithPort("/tax"));

        assertThat(result.statusCode()).isEqualTo(412);
    }

    @Test
    public void calculateTaxWithInvalidFuelTypeShouldReturnPreconditionFailed() {
        TaxRequestDTO input = new TaxRequestDTO(100, 100, "abc", LocalDate.now());

        var result = given()
                .contentType("application/json")
                .body(input)
                .when()
                .post(createURLWithPort("/tax"));

        assertThat(result.statusCode()).isEqualTo(412);
    }

    private String createURLWithPort(String uri) {
        return "http://localhost:" + port + uri;
    }
}
