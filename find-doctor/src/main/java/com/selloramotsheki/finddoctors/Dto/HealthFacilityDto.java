package com.selloramotsheki.finddoctors.Dto;

import com.selloramotsheki.finddoctors.enums.FacilityType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;

import java.util.List;

@Data
public class HealthFacilityDto {
    private Long id;
    private String name;
    private String address;
    @Enumerated(EnumType.STRING)
    private FacilityType type;
    private List<DoctorDto> doctors;
}
