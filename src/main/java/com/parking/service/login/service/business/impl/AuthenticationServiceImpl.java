package com.parking.service.login.service.business.impl;

import com.parking.service.login.client.UtpInterfaceClient;
import com.parking.service.login.client.dto.RequestUtpClient;
import com.parking.service.login.client.dto.ResponseUtpClient;
import com.parking.service.login.controller.dto.RequestDto;
import com.parking.service.login.controller.dto.ResponseDto;
import com.parking.service.login.controller.dto.VehicleDto;
import com.parking.service.login.entities.AccountEntity;
import com.parking.service.login.entities.Vehicles;
import com.parking.service.login.service.business.AccountService;
import com.parking.service.login.service.business.AuthenticationService;
import com.parking.service.login.service.business.VehicleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthenticationServiceImpl implements AuthenticationService {

    private final UtpInterfaceClient utpInterfaceClient;
    private final AccountService accountService;
    private final VehicleService vehicleService;

    @Override
    public ResponseDto userAuthentication(RequestDto requestDto) {
        Optional<ResponseUtpClient> utpClient = utpInterfaceClient.getUserUtp(new RequestUtpClient(requestDto.getUsername(), requestDto.getPassword()));
        //log.info("Response UTP-INTERFACE: {}", utpClient.get());
        if(utpClient.isPresent()){
            log.info("Response UTP-INTERFACE: {}", utpClient.get());
            Optional<AccountEntity> account = accountService.getAccountByUsername(utpClient.get().getUsername());
            if(account.isPresent()){
                Optional<Vehicles> vehicles = vehicleService.finVehicleById(account.get().getVehicleId());
                return ResponseDto.builder()
                        .username(utpClient.get().getUsername())
                        .campus(utpClient.get().getCampus())
                        .valid(Boolean.TRUE)
                        .message("El usuario es valido.")
                        .names(account.get().getNames().concat(" ").concat(account.get().getLastnames()))
                        .vehicleDto(buildVehicleDto(vehicles))
                        .qr(account.get().getQrCode())
                        .build();
            }else {
                return ResponseDto.builder()
                        .valid(Boolean.FALSE)
                        .message("El Usuario no tiene cuenta!")
                        .build();
            }

        }else {
            return ResponseDto.builder()
                    .valid(Boolean.FALSE)
                    .message("El usuario no existe!")
                    .build();
        }
    }

    private VehicleDto buildVehicleDto(Optional<Vehicles> vehicles) {
        return vehicles.map(value -> VehicleDto.builder()
                .placa(value.getPlaca())
                .type(value.getType())
                .modelo(value.getModel())
                .build()).orElse(null);

    }
}
