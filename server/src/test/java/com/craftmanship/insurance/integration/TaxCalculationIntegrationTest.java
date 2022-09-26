package com.craftmanship.insurance.integration;

import com.craftmanship.insurance.InsuranceServicesApplication;
import com.craftmanship.insurance.model.TaxRequest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpHeaders;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.time.LocalDate;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = InsuranceServicesApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TaxCalculationIntegrationTest {
    @LocalServerPort
    private int port;

    TestRestTemplate restTemplate = new TestRestTemplate();

    HttpHeaders headers = new HttpHeaders();

    @Test
    public void healthCheck() {
        TaxRequest input = new TaxRequest(135, 110, "Benzin", LocalDate.of(2021, 01, 01));
        given()
                .contentType("application/json")
                .body(input)
                .when()
                .post(createURLWithPort("/tax"))
                .then()
                .statusCode(200);
    }

    @Test
    public void calculateInsurance() {
        assertEquals(
                BigDecimal.valueOf(83.60).setScale(2),
                createRequest(135, 110, "Benzin", LocalDate.of(2021, 01, 01))
                );
    }

    private BigDecimal createRequest(int co2, int kw, String fuel, LocalDate registrationDate) {
        TaxRequest input = new TaxRequest(co2, kw, fuel, registrationDate);

        return given()
                        .contentType("application/json")
                        .body(input)
                        .when()
                        .post(createURLWithPort("/tax"))
                        .as(BigDecimal.class);
    }


    private String createURLWithPort(String uri) {
        return "http://localhost:" + port + uri;
    }
}
