package com.craftmanship.insurance.integration;

import com.craftmanship.insurance.InsuranceServicesApplication;
import com.craftmanship.insurance.model.CarInsuranceInputDTO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpHeaders;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = InsuranceServicesApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PremiumCalculationIntegrationTest {
    @LocalServerPort
    private int port;

    @Test
    public void calculatePremium() {
        CarInsuranceInputDTO input = new CarInsuranceInputDTO(100, 9, 1000);
        BigDecimal result =
        given()
                .contentType("application/json")
                .body(input)
                .when()
                .post(createURLWithPort("/premium"))
                .as(BigDecimal.class);

        assertEquals(result, BigDecimal.valueOf(83.60).setScale(2));
    }

    private String createURLWithPort(String uri) {
        return "http://localhost:" + port + uri;
    }
}
