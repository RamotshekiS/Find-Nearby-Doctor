package com.selloramotsheki.finddoctors.service.Specialization;

import com.selloramotsheki.finddoctors.model.Specialization;

import java.util.List;

public interface ISpecializationService {
    Specialization getSpecializationById(Long id);
    Specialization addSpecialization(Specialization specialization);
    List<Specialization> getAllSpecialization();
}
