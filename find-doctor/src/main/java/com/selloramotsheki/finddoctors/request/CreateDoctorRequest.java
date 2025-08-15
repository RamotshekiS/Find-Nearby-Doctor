package com.selloramotsheki.finddoctors.request;

import com.selloramotsheki.finddoctors.model.Specialization;
import lombok.Data;

@Data
public class CreateDoctorRequest {
    private Long id;
    private String firstName;
    private String lastName;
    private  String email;
    private String phone;
    private String city;
    private String suburb;
    private String postalCode;
    private boolean verified;
    private Specialization specialization;
    private Long facilityId;
}
