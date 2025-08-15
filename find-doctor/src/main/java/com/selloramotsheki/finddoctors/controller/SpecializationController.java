package com.selloramotsheki.finddoctors.controller;

import com.selloramotsheki.finddoctors.exceptions.AlreadyExistsException;
import com.selloramotsheki.finddoctors.model.Specialization;
import com.selloramotsheki.finddoctors.response.ApiResponse;
import com.selloramotsheki.finddoctors.service.Specialization.ISpecializationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.CONFLICT;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

@RequiredArgsConstructor
@RestController
@RequestMapping("${api.prefix}/specialization")
public class SpecializationController {
    private final ISpecializationService specializationService;

    @PostMapping("/add")
    public ResponseEntity<ApiResponse> createSpecialization(@RequestBody Specialization name) {
        try {
            Specialization theSpecialization = specializationService.addSpecialization(name);
            return ResponseEntity.ok(new ApiResponse("Succes", theSpecialization));
        } catch (AlreadyExistsException e) {
            return ResponseEntity.status(CONFLICT).body(new ApiResponse(e.getMessage(), null));
        }
    }

    @GetMapping("/specializations")
    public ResponseEntity<ApiResponse> getAllSpecializations() {
        try {
            List<Specialization> specializations = specializationService.getAllSpecialization();
            return ResponseEntity.ok(new ApiResponse("Success", specializations));
        } catch (Exception e) {
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new ApiResponse(e.getMessage(), null));
        }
    }

}
