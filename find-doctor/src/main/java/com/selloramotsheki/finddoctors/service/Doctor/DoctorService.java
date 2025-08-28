package com.selloramotsheki.finddoctors.service.Doctor;

import com.selloramotsheki.finddoctors.Dto.DoctorDto;
import com.selloramotsheki.finddoctors.exceptions.ResourceNotFound;
import com.selloramotsheki.finddoctors.model.Doctor;
import com.selloramotsheki.finddoctors.model.HealthFacility;
import com.selloramotsheki.finddoctors.model.Specialization;
import com.selloramotsheki.finddoctors.repository.DoctorRepository;
import com.selloramotsheki.finddoctors.repository.HealthFacilityRepository;
import com.selloramotsheki.finddoctors.repository.SpecializationRepository;
import com.selloramotsheki.finddoctors.request.CreateDoctorRequest;
import com.selloramotsheki.finddoctors.request.UpdateDoctorRequest;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

//@RequiredArgsConstructor
@Service
@RequiredArgsConstructor
public class DoctorService implements IDoctorService{
    private final DoctorRepository doctorRepository;
    private final SpecializationRepository specializationRepository;
    private final HealthFacilityRepository facilityRepository;
    private final ModelMapper modelMapper;


    @Override
    public Doctor getDoctorById(Long doctorId) {
        return doctorRepository.findById(doctorId)
                .orElseThrow(() -> new ResourceNotFound("Doctor not found"));
    }

    @Override
    public Doctor createDoctor(CreateDoctorRequest request) {
        // 1. Fetch the facility
        HealthFacility facility = facilityRepository.findById(request.getFacilityId())
                .orElseThrow(() -> new ResourceNotFound("Facility not found with id: " + request.getFacilityId()));

        // 2. Find or create specialization
        Specialization specialization = Optional.ofNullable(
                specializationRepository.findByName(request.getSpecialization().getName())
        ).orElseGet(() -> {
            Specialization newSpecialization = new Specialization(request.getSpecialization().getName());
            return specializationRepository.save(newSpecialization);
        });

        // 3. Create doctor and set facility + specialization
        Doctor doctor = new Doctor(
                request.getFirstName(),
                request.getLastName(),
                request.getEmail(),
                request.getPhone(),
                request.getCity(),
                request.getSuburb(),
                request.getPostalCode(),
                request.isVerified(),
                specialization
        );
        doctor.setHealthFacility(facility);

        // 4. Save and return
        return doctorRepository.save(doctor);

    }

//    private Doctor createDoctor(CreateDoctorRequest request, Specialization specialization) {
//
//        return new Doctor(
//                request.getFirstName(),
//                request.getLastName(),
//                request.getEmail(),
//                request.getPhone(),
//                request.getCity(),
//                request.getSuburb(),
//                request.getPostalCode(),
//                request.isVerified(),
//                specialization
//        );
//    }

    @Override
    public Doctor updateDoctor(UpdateDoctorRequest doctor, Long doctorId) {

        return null;
    }

    @Override
    public void DeleteDoctor(Long doctorId) {
        doctorRepository.findById(doctorId)
                .ifPresentOrElse(doctorRepository :: delete, () ->{
                    throw new ResourceNotFound("Doctor not found!");
                });
    }

    @Override
    public List<Doctor> getAllDoctors() {
        return doctorRepository.findAll();
    }

    @Override
    public List<Doctor> getDoctorsByCity(String city) {
        return doctorRepository.findDoctorByCity(city);
    }

    @Override
    public List<Doctor> getByCityAndSpecialization(String city, String specialization) {
        return doctorRepository.findByCityAndSpecialization_Name(city, specialization);
    }

    @Override
    public DoctorDto convertToDto(Doctor doctor) {
        return modelMapper.map(doctor, DoctorDto.class);
    }

    @Override
    public List<DoctorDto> getConvertedDoctors(List<Doctor> doctors) {
        return doctors.stream()
                .map(this::convertToDto)
                .toList();
    }
}
