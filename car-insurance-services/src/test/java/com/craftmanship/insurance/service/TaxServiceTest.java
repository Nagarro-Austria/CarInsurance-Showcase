package com.craftmanship.insurance.service;

import com.craftmanship.insurance.model.TaxRequestDTO;

import com.craftmanship.insurance.model.TaxResponseDTO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.math.BigDecimal;
import java.time.LocalDate;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;

class TaxServiceTest {
}