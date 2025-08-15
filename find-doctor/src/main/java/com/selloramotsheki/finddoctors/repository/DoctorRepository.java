package com.selloramotsheki.finddoctors.repository;

import com.selloramotsheki.finddoctors.model.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DoctorRepository extends JpaRepository<Doctor, Long> {
    //List<Doctor> findDoctorByName (String name);
    boolean existsByEmail(String email);
    List<Doctor> findDoctorByCity (String city);
    List<Doctor> findByCityAndSpecialization_Name(String city, String specialization);

}
