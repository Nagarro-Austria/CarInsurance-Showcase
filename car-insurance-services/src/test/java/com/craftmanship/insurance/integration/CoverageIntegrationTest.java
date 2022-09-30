package com.craftmanship.insurance.integration;

import com.craftmanship.insurance.model.CoverageResponseDTO;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;

public class CoverageIntegrationTest {
    private int port = 8080;

    private String createURLWithPort(String uri) {
        return "http://localhost:" + port + uri;
    }

    @Test
    public void shouldReadOnlyValidCoverageRessources() {

        // TODO: use REST Assured to call the server
        // - apply the right Htpp protocol
        // - reading the result is different since it's a list:
        //     ...extract().body().jsonPath().getList(".", MyClass.class);
        /*
        var result = RestAssured.given()
                .contentType("application/json")
                .when()
                ...
        */
        // TODO: add your assertion(s)
        // - tip: use either REST Assured or AssertJ to simplify the assertion code
        Assertions.assertThat("this test").isEqualTo("fails");
    }
}
