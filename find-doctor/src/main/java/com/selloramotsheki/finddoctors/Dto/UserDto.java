package com.selloramotsheki.finddoctors.Dto;

import lombok.Data;

import java.util.List;

@Data
public class UserDto {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private List<AppointmentDto> appointments;
}
