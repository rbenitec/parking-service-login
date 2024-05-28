package com.parking.service.login.client.dto;

import lombok.Data;

@Data
public class ResponseUtpClient {
    private String username;
    private String email;
    private String createdAt;
    private String names;
    private String lastname;
    private String dni;
}
