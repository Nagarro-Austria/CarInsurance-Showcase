package com.craftmanship.insurance.integration;

import com.craftmanship.insurance.InsuranceServicesApplication;
import com.craftmanship.insurance.model.TaxRequestDTO;
import com.craftmanship.insurance.model.TaxResponseDTO;
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

    @ParameterizedTest
    @CsvSource({"110, Benzin, 83.60"})
    public void calculateTax(String power, String fuelType, String expectedTax) {

        TaxRequestDTO input = new TaxRequestDTO(135, Integer.valueOf(power), fuelType, LocalDate.of(2021, 01, 01));

        var result = given()
                .contentType("application/json")
                .body(input)
                .when()
                .post(createURLWithPort("/tax"))
                .as(TaxResponseDTO.class).tax();

        assertThat(result).isEqualTo(new BigDecimal(expectedTax));
    }


    private String createURLWithPort(String uri) {
        return "http://localhost:" + port + uri;
    }
}
