package com.craftmanship.insurance.repositories;

import com.craftmanship.insurance.entities.Coverage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Optional;

@Repository
public interface CoverageRepository extends JpaRepository<Coverage, Long> {

    @Query("SELECT f FROM Coverage f")
    Collection<Coverage> findAllCoverages();
}
