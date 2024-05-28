package com.parking.service.login.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
@Table(name = "account")
public class AccountEntity {
    @Id
    private String username;
    private String names;
    private String lastnames;
    private String dni;
    private String mail;
    private String password;
}
