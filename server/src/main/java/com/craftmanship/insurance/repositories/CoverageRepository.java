package com.craftmanship.insurance.repositories;

import com.craftmanship.insurance.entities.Coverage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CoverageRepository extends JpaRepository<Coverage, Long> {
}
