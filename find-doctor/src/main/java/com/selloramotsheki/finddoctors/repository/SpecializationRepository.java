package com.selloramotsheki.finddoctors.repository;

import com.selloramotsheki.finddoctors.model.Specialization;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpecializationRepository extends JpaRepository<Specialization, Long> {
    Specialization findByName(String name);

    boolean existsByName(String name);
}
