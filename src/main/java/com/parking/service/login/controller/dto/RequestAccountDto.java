package com.parking.service.login.controller.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RequestAccountDto {
    private String username;
    private String password;
    private VehicleDto vehicleDto;
}
