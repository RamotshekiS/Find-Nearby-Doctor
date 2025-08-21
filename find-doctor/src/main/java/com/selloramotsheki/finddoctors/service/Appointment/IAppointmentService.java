package com.selloramotsheki.finddoctors.service.Appointment;

import com.selloramotsheki.finddoctors.Dto.AppointmentDto;
import com.selloramotsheki.finddoctors.model.Appointment;

import java.time.LocalDateTime;

public interface IAppointmentService {
    Appointment bookAppointment(Long userId, Long doctorId, LocalDateTime appointmentDate);
    AppointmentDto convertAppointmentToDto(Appointment appointment);
}
