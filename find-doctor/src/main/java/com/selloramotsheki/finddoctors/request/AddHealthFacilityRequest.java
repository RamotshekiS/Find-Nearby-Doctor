package com.selloramotsheki.finddoctors.request;

import com.selloramotsheki.finddoctors.enums.FacilityType;
import lombok.Data;

@Data
public class AddHealthFacilityRequest {
    private Long id;
    private String name;
    private String address;
    private FacilityType type;
}
