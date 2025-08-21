package com.selloramotsheki.finddoctors.repository;

import com.selloramotsheki.finddoctors.model.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppoitmentRepository extends JpaRepository<Appointment, Long> {
    Appointment findByUserId(Long userId);
    Appointment findByDoctorId(Long doctorId);
}
