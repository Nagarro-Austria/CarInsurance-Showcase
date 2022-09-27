package com.craftmanship.insurance.integration;

import com.craftmanship.insurance.InsuranceServicesApplication;
import com.craftmanship.insurance.model.TaxRequestDTO;
import com.craftmanship.insurance.model.TaxResponseDTO;
import com.craftmanship.insurance.service.TaxService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.time.LocalDate;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = InsuranceServicesApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TaxCalculationIntegrationTest {
    @LocalServerPort
    private int port;

    @Test
    public void calculateTaxForElectric() {

        TaxRequestDTO input = new TaxRequestDTO(100, 100, "electricity", LocalDate.of(2022, 4, 21));

        assertThat(callService(input)).isEqualTo(new BigDecimal("0.00"));
    }

    @ParameterizedTest
    @CsvSource({"165, 215, 144.00",
            "110, 135, 46.80",
            "65, 115, 7.20",})
    public void calculateTaxForGasoline(int power, int co2Emissions, String expectedTax) {

        TaxRequestDTO input = new TaxRequestDTO(co2Emissions, power, "gasoline", LocalDate.of(2022, 4, 21));

        assertThat(callService(input)).isEqualTo(new BigDecimal(expectedTax));
    }

    @ParameterizedTest
    @CsvSource({"165, 215, 144.00",
            "110, 135, 46.80",
            "65, 115, 7.20",})
    public void calculateTaxForDiesel(int power, int co2Emissions, String expectedTax) {

        TaxRequestDTO input = new TaxRequestDTO(co2Emissions, power, "diesel", LocalDate.of(2022, 4, 21));

        assertThat(callService(input)).isEqualTo(new BigDecimal(expectedTax));
    }

    @ParameterizedTest
    @CsvSource({"165, 215, 72.00",
            "110, 135, 32.40",
            "65, 115, 3.60",})
    public void calculateTaxForHybrid(int power, int co2Emissions, String expectedTax) {

        TaxRequestDTO input = new TaxRequestDTO(co2Emissions, power, "hybrid", LocalDate.of(2022, 4, 21));

        assertThat(callService(input)).isEqualTo(new BigDecimal(expectedTax));
    }

    private BigDecimal callService(TaxRequestDTO input) {
        var result = given()
                .contentType("application/json")
                .body(input)
                .when()
                .post(createURLWithPort("/tax"))
                .as(TaxResponseDTO.class).tax();

        return result;
    }

    private String createURLWithPort(String uri) {
        return "http://localhost:" + port + uri;
    }
}
