package com.parking.service.login.controller.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseDto {
    private Boolean valid;
    private String message;
    private String username;
    private String campus;
    private String names;
    private String qr;
    private VehicleDto vehicleDto;
}
