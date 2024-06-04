package com.parking.service.login.service.business.impl;

import com.parking.service.login.client.UtpInterfaceClient;
import com.parking.service.login.client.dto.RequestUtpClient;
import com.parking.service.login.client.dto.ResponseUtpClient;
import com.parking.service.login.controller.dto.RequestAccountDto;
import com.parking.service.login.controller.dto.RequestDto;
import com.parking.service.login.controller.dto.ResponseDto;
import com.parking.service.login.controller.dto.VehicleDto;
import com.parking.service.login.entities.AccountEntity;
import com.parking.service.login.entities.Vehicles;
import com.parking.service.login.repository.AccountRepository;
import com.parking.service.login.repository.VehicleRepository;
import com.parking.service.login.service.business.AccountService;
import com.parking.service.login.service.business.VehicleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;
    private final VehicleService vehicleService;
    private final UtpInterfaceClient utpInterfaceClient;

    @Override
    public ResponseDto createdAccount(RequestAccountDto request) {
        //ValidarDatos en la inerface de utp
        Optional<ResponseUtpClient> utpClient = utpInterfaceClient.getUserUtp(new RequestUtpClient(request.getUsername(), request.getPassword()));
        //OK -> Prcede a crear la cuenta
        if(utpClient.isPresent()){
            Vehicles vehicles = vehicleService.saveVehicle(builEntityVehicle(request.getVehicle()));
            AccountEntity accountEntity = accountRepository.save(buildAccount(utpClient.get(), request, vehicles));
            return buildResponseDto(accountEntity);
        }
        //Dont Exist -> Responde que no existe el usuario
        return ResponseDto.builder()
                .valid(Boolean.FALSE)
                .message("Hubo un error al crear el usuario.")
                .build();
    }

    private Vehicles builEntityVehicle(VehicleDto vehicleDto) {
        return Vehicles.builder()
                .model(vehicleDto.getModelo())
                .placa(vehicleDto.getPlaca())
                .type(vehicleDto.getType())
                .build();
    }

    private ResponseDto buildResponseDto(AccountEntity accountEntity) {
        return ResponseDto.builder()
                .valid(Boolean.TRUE)
                .username(accountEntity.getUsername())
                .message("Usuario creado con exito.")
                .build();
    }


    @Override
    public AccountEntity saveAccount(AccountEntity accountEntity) {
        return accountRepository.save(accountEntity);
    }

    @Override
    public AccountEntity getAccountByUsername(String username) {
        return accountRepository.getReferenceById(username);
    }

    @Override
    public void deleteAccountByUsername(String dni) {
        accountRepository.deleteById(dni);
    }

    @Override
    public List<AccountEntity> getAllAccount() {
        return accountRepository.findAll();
    }
    private AccountEntity buildAccount(ResponseUtpClient responseUtpClient, RequestAccountDto request, Vehicles vehicles) {
        return AccountEntity.builder()
                .dni(responseUtpClient.getDni())
                .lastnames(responseUtpClient.getLastname())
                .email(responseUtpClient.getEmail())
                .names(responseUtpClient.getNames())
                .password(request.getPassword())
                .username(responseUtpClient.getUsername())
                .status(true)
                .accountId(vehicles.getId())
                .build();
    }
}
