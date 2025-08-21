package com.selloramotsheki.finddoctors.service.Appointment;

import com.selloramotsheki.finddoctors.Dto.AppointmentDto;
import com.selloramotsheki.finddoctors.enums.AppointmentStatus;
import com.selloramotsheki.finddoctors.exceptions.ResourceNotFound;
import com.selloramotsheki.finddoctors.model.Appointment;
import com.selloramotsheki.finddoctors.model.Doctor;
import com.selloramotsheki.finddoctors.model.User;
import com.selloramotsheki.finddoctors.repository.AppoitmentRepository;
import com.selloramotsheki.finddoctors.repository.DoctorRepository;
import com.selloramotsheki.finddoctors.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class AppointmentService implements IAppointmentService{
    private final AppoitmentRepository appoitmentRepository;
    private final UserRepository userRepository;
    private final DoctorRepository doctorRepository;
    private final ModelMapper modelMapper;

    @Override
    public Appointment bookAppointment(Long userId, Long doctorId, LocalDateTime appointmentDate) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFound("User not found!"));
        Doctor doctor = doctorRepository.findById(doctorId)
                .orElseThrow(() -> new ResourceNotFound("Doctor not found!"));

        Appointment appointment = new Appointment();
        appointment.setUser(user);
        appointment.setDoctor(doctor);
        appointment.setAppointmentDate(appointmentDate);
        appointment.setStatus(AppointmentStatus.PENDING);

        return appoitmentRepository.save(appointment);
    }

    @Override
    public AppointmentDto convertAppointmentToDto(Appointment appointment) {
        return modelMapper.map(appointment, AppointmentDto.class);
    }
}
