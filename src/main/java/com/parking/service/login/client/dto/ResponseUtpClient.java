package com.parking.service.login.client.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseUtpClient {
    private String username;
    private String password;
    private String email;
    private LocalDate createdAt;
    private String names;
    private String lastname;
    private String dni;
}
