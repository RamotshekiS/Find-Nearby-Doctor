package com.selloramotsheki.finddoctors.repository;

import com.selloramotsheki.finddoctors.model.HealthFacility;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HealthFacilityRepository extends JpaRepository<HealthFacility, Long> {

    boolean existsByName(String name);
}
