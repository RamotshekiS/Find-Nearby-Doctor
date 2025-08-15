package com.selloramotsheki.finddoctors.service.Specialization;

import com.selloramotsheki.finddoctors.exceptions.AlreadyExistsException;
import com.selloramotsheki.finddoctors.exceptions.ResourceNotFound;
import com.selloramotsheki.finddoctors.model.Specialization;
import com.selloramotsheki.finddoctors.repository.SpecializationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class SpecializationService implements ISpecializationService{
    private final SpecializationRepository specializationRepository;


    @Override
    public Specialization getSpecializationById(Long id) {
        return specializationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFound("Specialization not found!"));
    }

    @Override
    public Specialization addSpecialization(Specialization specialization) {
        return Optional.of(specialization)
                .filter(s -> !specializationRepository.existsByName(specialization.getName()))
                .map(specializationRepository :: save)
                .orElseThrow(() -> new AlreadyExistsException("Specialization " + specialization.getName() + " already exiss"));
    }

    @Override
    public List<Specialization> getAllSpecialization() {
        return specializationRepository.findAll();
    }
}
