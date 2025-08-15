package com.selloramotsheki.finddoctors.request;

import lombok.Data;

@Data
public class UpdateDoctorRequest {
    private String firstName;
    private String lastName;
    private String phone;
    private boolean verified;
}
