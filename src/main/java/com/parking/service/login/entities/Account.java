package com.parking.service.login.entities;

import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
@Builder
@Table(name = "account")
public class Account {
    @Id
    private String username;
    private String names;
    private String lastnames;
    private String dni;
    private String mail;
    private String password;
}
