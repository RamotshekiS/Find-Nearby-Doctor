package com.selloramotsheki.finddoctors.service.HealthFacility;

import com.selloramotsheki.finddoctors.Dto.HealthFacilityDto;
import com.selloramotsheki.finddoctors.exceptions.AlreadyExistsException;
import com.selloramotsheki.finddoctors.exceptions.ResourceNotFound;
import com.selloramotsheki.finddoctors.model.HealthFacility;
import com.selloramotsheki.finddoctors.repository.HealthFacilityRepository;
import com.selloramotsheki.finddoctors.request.AddHealthFacilityRequest;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class HealthFacilityService implements IHealthFacilityService{
    private final HealthFacilityRepository facilityRepository;
    private final ModelMapper modelMapper;

    @Override
    public HealthFacility addHealthFacility(AddHealthFacilityRequest request) {
//        return Optional.of(request)
//                .filter(req -> !facilityRepository.existsByName(request.getName()))
//                .map(req -> {
//                    HealthFacility facility = new HealthFacility();
//                    request.setName(request.getName());
//                    request.setAddress(request.getAddress());
//                    request.setType(request.getType());
//                    return facilityRepository.save(facility);
//                }).orElseThrow(() -> new  AlreadyExistsException("Facility with the name " + request.getName() + "already exists"));


            HealthFacility facility = new HealthFacility(
                    request.getName(),
                    request.getType(),
                    request.getAddress()
            );
            return facilityRepository.save(facility);
    }

    @Override
    public List<HealthFacility> getAllHealthFacility() {
        return facilityRepository.findAll();
    }

    @Override
    public HealthFacility getHealthFacilityById(Long id) {
        return facilityRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFound("facility not found!"));
    }

    @Override
    public void deleteHealthFacility(Long id) {
        facilityRepository.findById(id).ifPresentOrElse(facilityRepository::delete, () -> {
            throw new ResourceNotFound("Faciltiy not found!");
        });
    }

    @Override
    public HealthFacilityDto convertToDto(HealthFacility facility) {
        return modelMapper.map(facility, HealthFacilityDto.class);
    }

    @Override
    public List<HealthFacilityDto> getConvertedFacility(List<HealthFacility> facilities) {
        return facilities.stream()
                .map(this::convertToDto)
                .toList();
    }


}
