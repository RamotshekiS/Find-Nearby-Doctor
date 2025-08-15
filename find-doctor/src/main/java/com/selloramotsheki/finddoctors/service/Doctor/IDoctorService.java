package com.selloramotsheki.finddoctors.service.Doctor;

import com.selloramotsheki.finddoctors.Dto.DoctorDto;
import com.selloramotsheki.finddoctors.model.Doctor;
import com.selloramotsheki.finddoctors.request.CreateDoctorRequest;
import com.selloramotsheki.finddoctors.request.UpdateDoctorRequest;

import java.util.List;

public interface IDoctorService {
    Doctor getDoctorById(Long doctorId);
    Doctor createDoctor(CreateDoctorRequest request);
    Doctor updateDoctor(UpdateDoctorRequest doctor, Long doctorId);
    void DeleteDoctor(Long doctorId);
    //List<Doctor> getDoctorByName(String name);
    List<Doctor> getAllDoctors();
    List<Doctor> getDoctorsByCity(String city);
    List<Doctor> getByCityAndSpecialization(String city, String specialization);

    DoctorDto convertToDto(Doctor doctor);

    List<DoctorDto> getConvertedDoctors(List<Doctor> doctors);
}
