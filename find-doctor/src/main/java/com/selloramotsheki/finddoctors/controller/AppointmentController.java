package com.selloramotsheki.finddoctors.controller;


import com.selloramotsheki.finddoctors.Dto.AppointmentDto;
import com.selloramotsheki.finddoctors.model.Appointment;
import com.selloramotsheki.finddoctors.response.ApiResponse;
import com.selloramotsheki.finddoctors.service.Appointment.IAppointmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@RestController
@RequestMapping("${api.prefix}/appointments")
public class AppointmentController {
    private final IAppointmentService appointmentService;

    @PostMapping("/book-appointment")
    public ResponseEntity<ApiResponse> bookAppointment(@RequestParam Long userId,
                                                       @RequestParam Long doctorId,
                                                       @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dateTime) {
        try {
            Appointment appointment = appointmentService.bookAppointment(userId, doctorId, dateTime);
            AppointmentDto appointmentDto = appointmentService.convertAppointmentToDto(appointment);
            return ResponseEntity.ok(new ApiResponse("Appointment booked successfully", appointmentDto));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse("Error Occured", e.getMessage()));
        }
    }
}
