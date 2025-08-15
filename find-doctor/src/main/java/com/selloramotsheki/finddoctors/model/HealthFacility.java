package com.selloramotsheki.finddoctors.model;


import com.selloramotsheki.finddoctors.enums.FacilityType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class HealthFacility {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String address;
    @Enumerated(EnumType.STRING)
    private FacilityType type; // CLINIC, HOSPITAL, SURGERY

    @OneToMany(mappedBy = "healthFacility", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Doctor> doctors;

    public HealthFacility(String name, FacilityType type, String address) {
        this.name = name;
        this.type = type;
        this.address = address;
    }
}
