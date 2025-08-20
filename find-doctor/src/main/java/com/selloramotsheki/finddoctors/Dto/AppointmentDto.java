package com.selloramotsheki.finddoctors.Dto;

import com.selloramotsheki.finddoctors.enums.AppointmentStatus;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class AppointmentDto {
    private Long id;
    private LocalDateTime appointmentDate;
    @Enumerated(EnumType.STRING)
    private AppointmentStatus status;

}

