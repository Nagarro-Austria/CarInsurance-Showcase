package com.craftmanship.insurance.integration;

import com.craftmanship.insurance.InsuranceServicesApplication;
import com.craftmanship.insurance.model.CarInsuranceInputDTO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = InsuranceServicesApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class InsuranceCalculationIntegrationTest {
    @LocalServerPort
    private int port;

    TestRestTemplate restTemplate = new TestRestTemplate();

    HttpHeaders headers = new HttpHeaders();

    @Test
    public void calculateInsurance() {

        CarInsuranceInputDTO input = new CarInsuranceInputDTO(100, 9, 1000);

        HttpEntity<CarInsuranceInputDTO> entity = new HttpEntity<>(input, headers);

        ResponseEntity<BigDecimal> response = restTemplate.exchange(
                createURLWithPort("/insurance"),
                HttpMethod.POST, entity, BigDecimal.class);

        assertEquals(response.getBody(), BigDecimal.valueOf(83.60).setScale(2));
    }

    @Test
    public void calculateInsuranceHighestInsurancePremium() {

        CarInsuranceInputDTO input = new CarInsuranceInputDTO(147, 17, 8000);

        HttpEntity<CarInsuranceInputDTO> entity = new HttpEntity<>(input, headers);

        ResponseEntity<BigDecimal> response = restTemplate.exchange(
                createURLWithPort("/insurance"),
                HttpMethod.POST, entity, BigDecimal.class);

        assertEquals(response.getBody(), BigDecimal.valueOf(277.2).setScale(2));
    }

    @Test
    public void calculateInsuranceLowestInsurancePremium() {

        CarInsuranceInputDTO input = new CarInsuranceInputDTO(10, 1, 1000);

        HttpEntity<CarInsuranceInputDTO> entity = new HttpEntity<>(input, headers);

        ResponseEntity<BigDecimal> response = restTemplate.exchange(
                createURLWithPort("/insurance"),
                HttpMethod.POST, entity, BigDecimal.class);

        assertEquals(response.getBody(), BigDecimal.valueOf(11.31).setScale(2));
    }

    private String createURLWithPort(String uri) {
        return "http://localhost:" + port + uri;
    }
}
