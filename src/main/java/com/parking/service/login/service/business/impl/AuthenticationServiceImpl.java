package com.parking.service.login.service.business.impl;

import com.parking.service.login.client.UtpInterfaceClient;
import com.parking.service.login.client.dto.RequestUtpClient;
import com.parking.service.login.client.dto.ResponseUtpClient;
import com.parking.service.login.controller.dto.RequestDto;
import com.parking.service.login.controller.dto.ResponseDto;
import com.parking.service.login.service.business.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    private final UtpInterfaceClient utpInterfaceClient;

    @Override
    public ResponseDto userAuthentication(RequestDto requestDto) {
        Optional<ResponseUtpClient> utpClient = utpInterfaceClient.getUserUtp(new RequestUtpClient(requestDto.getUsername()));
        return utpClient.map(responseUtpClient -> ResponseDto.builder()
                .valid(Boolean.TRUE)
                .username(responseUtpClient.getUsername())
                .message("El usuario es valido.")
                .build())
                .orElse(ResponseDto.builder()
                        .valid(Boolean.FALSE)
                        .message("El usuario no existe")
                        .build());
    }
}
