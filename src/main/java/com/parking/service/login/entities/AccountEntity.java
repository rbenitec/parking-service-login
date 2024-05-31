package com.parking.service.login.entities;

import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "account")
public class AccountEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;
    @Column(name = "vehicles_id")
    private Integer vehiclesId;
    private String email;
    private String password;
    @Column(name = "names")
    private String names;
    @Column(name = "apellidos")
    private String lastnames;
    @Column(name = "estado")
    private Boolean status;
    private String dni;
    private String username;
}
