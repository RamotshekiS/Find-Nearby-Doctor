package com.selloramotsheki.finddoctors.service.HealthFacility;

import com.selloramotsheki.finddoctors.Dto.HealthFacilityDto;
import com.selloramotsheki.finddoctors.model.HealthFacility;
import com.selloramotsheki.finddoctors.request.AddHealthFacilityRequest;

import java.util.List;

public interface IHealthFacilityService {
    HealthFacility addHealthFacility(AddHealthFacilityRequest request);
    List<HealthFacility> getAllHealthFacility();
    HealthFacility getHealthFacilityById(Long id);
    void deleteHealthFacility(Long id);

    public HealthFacilityDto convertToDto(HealthFacility facility);

    List<HealthFacilityDto> getConvertedFacility(List<HealthFacility> facilities);
}
