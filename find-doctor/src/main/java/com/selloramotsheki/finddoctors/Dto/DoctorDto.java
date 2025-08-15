package com.selloramotsheki.finddoctors.Dto;

import com.selloramotsheki.finddoctors.model.Appointment;
import com.selloramotsheki.finddoctors.model.HealthFacility;
import com.selloramotsheki.finddoctors.model.Specialization;
import lombok.Data;

import java.util.List;

@Data
public class DoctorDto {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String city;
    private String suburb;
    private String postalCode;
    private boolean verified;
    private Specialization specialization;
    //private HealthFacilityDto healthFacility;
    private List<AppointmentDto> appointments;

}
