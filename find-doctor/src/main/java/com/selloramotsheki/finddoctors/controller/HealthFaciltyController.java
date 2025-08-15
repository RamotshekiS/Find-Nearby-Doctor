package com.selloramotsheki.finddoctors.controller;

import com.selloramotsheki.finddoctors.Dto.HealthFacilityDto;
import com.selloramotsheki.finddoctors.exceptions.AlreadyExistsException;
import com.selloramotsheki.finddoctors.exceptions.ResourceNotFound;
import com.selloramotsheki.finddoctors.model.HealthFacility;
import com.selloramotsheki.finddoctors.request.AddHealthFacilityRequest;
import com.selloramotsheki.finddoctors.response.ApiResponse;
import com.selloramotsheki.finddoctors.service.HealthFacility.IHealthFacilityService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.CONFLICT;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@RequiredArgsConstructor
@RestController
@RequestMapping("${api.prefix}/facilities")
public class HealthFaciltyController {
    private final IHealthFacilityService facilityService;

    @PostMapping("/add")
    public ResponseEntity<ApiResponse> createHealthFacility(@RequestBody AddHealthFacilityRequest request) {
        try {
            HealthFacility facility = facilityService.addHealthFacility(request);
            HealthFacilityDto facilityDto = facilityService.convertToDto(facility);
            return ResponseEntity.ok(new ApiResponse("Create facility Success", facilityDto));
        } catch (AlreadyExistsException e) {
            return ResponseEntity.status(CONFLICT).body(new ApiResponse(e.getMessage(), null));
        }
    }

    @GetMapping("/facilities")
    public ResponseEntity<ApiResponse> getAllFacilities() {
        List<HealthFacility> facilities = facilityService.getAllHealthFacility();
        List<HealthFacilityDto> facilityDtos = facilityService.getConvertedFacility(facilities);
        return ResponseEntity.ok(new ApiResponse("Success", facilityDtos));
    }

    @DeleteMapping("/{id}/delete")
    public ResponseEntity<ApiResponse> deleteFacility(@PathVariable Long id) {
        try {
            facilityService.deleteHealthFacility(id);
            return  ResponseEntity.ok(new ApiResponse("Success", null));
        } catch (ResourceNotFound e) {
            return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage(), null));
        }

    }
}
