package com.craftmanship.insurance.integration;

import com.craftmanship.insurance.InsuranceServicesApplication;
import com.craftmanship.insurance.model.CoverageResponseDTO;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;

public class CoverageIntegrationTest {
    private int port = 8080;

    @Test
    public void shouldReadOnlyValidCoverageRessources() {

        CoverageResponseDTO result = given()
                .contentType("application/json")
                .when()
                .get(createURLWithPort("/coverage"))
                .then()
                .extract()
                .body()
                .as(CoverageResponseDTO.class);

        Assertions.assertThat(result.validFrom().getYear() >= LocalDate.now().getYear()).isTrue();
    }

    private String createURLWithPort(String uri) {
        return "http://localhost:" + port + uri;
    }
}
