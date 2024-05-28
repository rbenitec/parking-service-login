package com.parking.service.login.service.business.impl;

import com.parking.service.login.client.UtpInterfaceClient;
import com.parking.service.login.client.dto.RequestUtpClient;
import com.parking.service.login.client.dto.ResponseUtpClient;
import com.parking.service.login.controller.dto.RequestDto;
import com.parking.service.login.controller.dto.ResponseDto;
import com.parking.service.login.entities.Account;
import com.parking.service.login.repository.AccountRepository;
import com.parking.service.login.service.business.AccountService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;

    private final UtpInterfaceClient utpInterfaceClient;

    @Override
    public ResponseDto createdAccount(RequestDto request) {
        //ValidarDatos en la inerface de utp
        Optional<ResponseUtpClient> utpClient = utpInterfaceClient.getUserUtp(new RequestUtpClient(request.getUsername()));
        //OK -> Prcede a crear la cuenta
        if(utpClient.isPresent()){
            Account account = accountRepository.save(buildAccount(utpClient.get(), request));
            return buildResponseDto(account);
        }
        //Dont Exist -> Responde que no existe el usuario
        return ResponseDto.builder()
                .valid(Boolean.FALSE)
                .message("Hubo un error al crear el usuario.")
                .build();
    }

    private ResponseDto buildResponseDto(Account account) {
        return ResponseDto.builder()
                .valid(Boolean.TRUE)
                .username(account.getUsername())
                .message("Usuario creado con exito.")
                .build();
    }


    @Override
    public Account saveAccount(Account account) {
        return accountRepository.save(account);
    }

    @Override
    public Account getAccountByUsername(String username) {
        return accountRepository.getReferenceById(username);
    }

    @Override
    public void deleteAccountByUsername(String dni) {
        accountRepository.deleteById(dni);
    }

    @Override
    public List<Account> getAllAccount() {
        return accountRepository.findAll();
    }
    private Account buildAccount(ResponseUtpClient responseUtpClient, RequestDto request) {
        return Account.builder()
                .dni(responseUtpClient.getDni())
                .lastnames(responseUtpClient.getLastname())
                .mail(responseUtpClient.getEmail())
                .names(responseUtpClient.getNames())
                .password(request.getPassword())
                .username(responseUtpClient.getUsername())
                .build();
    }
}
