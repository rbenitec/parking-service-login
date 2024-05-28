package com.parking.service.login.controller.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class ResponseDto {
    private Boolean valid;
    private String message;
    private String username;
}
