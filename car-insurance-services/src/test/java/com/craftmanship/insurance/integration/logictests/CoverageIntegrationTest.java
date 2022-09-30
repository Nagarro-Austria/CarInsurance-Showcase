package com.craftmanship.insurance.integration.logictests;

import com.craftmanship.insurance.model.CoverageResponseDTO;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;

public class CoverageIntegrationTest {
    private int port = 8080;

    @Test
    public void shouldReadOnlyValidCoverageRessources() {

        List<CoverageResponseDTO> result = given()
                .contentType("application/json")
                .when()
                .get(createURLWithPort("/coverage"))
                .then()
                .extract()
                .body()
                .jsonPath().getList(".", CoverageResponseDTO.class);

        assertThat(result).allMatch(coverage -> coverage.getValidFrom().getYear() >= LocalDate.now().getYear());
    }

    private String createURLWithPort(String uri) {
        return "http://localhost:" + port + uri;
    }
}
