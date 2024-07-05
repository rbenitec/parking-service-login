package com.parking.service.login.model;

import com.parking.service.login.client.dto.ResponseUtpClient;
import com.parking.service.login.entities.Vehicles;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class QrData {
    private ResponseUtpClient client;
    private Vehicles vehicles;
}
