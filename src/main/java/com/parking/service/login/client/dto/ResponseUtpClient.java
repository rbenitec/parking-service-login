package com.parking.service.login.client.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseUtpClient {
    private String username;
    private String password;
    private String email;
    private String createdAt;
    private String names;
    private String lastname;
    private String dni;
    private String campus;
    private Integer accountId;
}
