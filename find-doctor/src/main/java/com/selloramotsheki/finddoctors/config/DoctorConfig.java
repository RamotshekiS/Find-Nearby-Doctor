package com.selloramotsheki.finddoctors.config;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.ui.Model;

@RequiredArgsConstructor
@Configuration
public class DoctorConfig {

    @Bean
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }
}
