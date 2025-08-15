package com.selloramotsheki.finddoctors.controller;

import com.selloramotsheki.finddoctors.Dto.DoctorDto;
import com.selloramotsheki.finddoctors.exceptions.AlreadyExistsException;
import com.selloramotsheki.finddoctors.exceptions.ResourceNotFound;
import com.selloramotsheki.finddoctors.model.Doctor;
import com.selloramotsheki.finddoctors.request.CreateDoctorRequest;
import com.selloramotsheki.finddoctors.response.ApiResponse;
import com.selloramotsheki.finddoctors.service.Doctor.IDoctorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("${api.prefix}/doctors")
public class DoctorController {
    private final IDoctorService doctorService;

    @PostMapping("/add")
    public ResponseEntity<ApiResponse> createDoctor(@RequestBody CreateDoctorRequest request){
        try {
            Doctor doctor = doctorService.createDoctor(request);
            DoctorDto doctorDto = doctorService.convertToDto(doctor);
            return ResponseEntity.ok(new ApiResponse("Create doctor success", doctorDto));
        } catch (AlreadyExistsException e) {
            return ResponseEntity.status(CONFLICT).body(new ApiResponse(e.getMessage(), null));
        }
    }

    @GetMapping("/{doctorId}/doctor")
    public ResponseEntity<ApiResponse> getDoctorById(@PathVariable Long doctorId){
        try {
            Doctor doctor = doctorService.getDoctorById(doctorId);
            DoctorDto doctorDto = doctorService.convertToDto(doctor);
            return ResponseEntity.ok(new ApiResponse("Success", doctorDto));
        } catch (ResourceNotFound e) {
            return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage(), null));
        }
    }

    @GetMapping("/all")
    public ResponseEntity<ApiResponse> getAllDoctors(){
        List<Doctor> doctors = doctorService.getAllDoctors();
        List<DoctorDto> doctorDto = doctorService.getConvertedDoctors(doctors);
        return ResponseEntity.ok(new ApiResponse("Success", doctorDto));
    }

    @DeleteMapping("{doctorId}/delete")
    public ResponseEntity<ApiResponse> deleteDoctor(@PathVariable Long doctorId){
        try {
            doctorService.DeleteDoctor(doctorId);
            return ResponseEntity.ok(new ApiResponse("Success", null));
        } catch (ResourceNotFound e) {
            return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage(), null));
        }
    }

    @GetMapping("/{city}/doctors")
    public ResponseEntity<ApiResponse> getDoctorByCity(@PathVariable String city) {
        try {
            List<Doctor> doctors = doctorService.getDoctorsByCity(city);
            List<DoctorDto> doctorDtos = doctorService.getConvertedDoctors(doctors);
            if(doctorDtos.isEmpty()){
                return ResponseEntity.status(NOT_FOUND).body(new ApiResponse("Doctor not found in this city", null));
            }
            return ResponseEntity.ok(new ApiResponse("Success", doctorDtos));
        } catch (Exception e) {
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new ApiResponse(e.getMessage(), null));
        }
    }

    @GetMapping("/by/city-and-specialization")
    public ResponseEntity<ApiResponse> getByCityAndSpecialization(@RequestParam String city, @RequestParam String specialization) {
        try {
            List<Doctor> doctors = doctorService.getByCityAndSpecialization(city, specialization);

            if(doctors.isEmpty()){
                return ResponseEntity.status(NOT_FOUND).body(new ApiResponse("Doctors not found in the city", null));
            }
            List<DoctorDto> convertedDoctors = doctorService.getConvertedDoctors(doctors);
            return ResponseEntity.ok(new ApiResponse("Success", convertedDoctors));
        } catch (Exception e) {
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new ApiResponse(e.getMessage(), null));
        }
    }
    
}
