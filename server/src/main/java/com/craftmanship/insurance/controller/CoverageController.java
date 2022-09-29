package com.craftmanship.insurance.controller;

import com.craftmanship.insurance.entities.Coverage;
import com.craftmanship.insurance.model.CoverageResponseDTO;
import com.craftmanship.insurance.repositories.CoverageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/coverage")
public class CoverageController {
    @Autowired
    private CoverageRepository coverageRepository;

    @GetMapping()
    public CoverageResponseDTO findValidCoverage() {

        Coverage result = coverageRepository.findValidCoverage();

        if (result == null)
            return null;

        return new CoverageResponseDTO(result.getId(), result.getValidFrom().toLocalDate(), result.getDescription() );
    }
}
