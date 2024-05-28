package com.parking.service.login.entities;

import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@Table(name = "account")
public class Vehicles {
    @Id
    private String placa;
}
