package com.selloramotsheki.finddoctors.response;

import com.selloramotsheki.finddoctors.model.Doctor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ApiResponse {
    String message;
    Object data;

    public ApiResponse(String message, Object data) {
        this.message = message;
        this.data = data;
    }
}
