package com.parking.service.login.controller;

import com.parking.service.login.controller.dto.RequestDto;
import com.parking.service.login.controller.dto.ResponseDto;
import com.parking.service.login.service.business.AccountService;
import com.parking.service.login.service.business.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
@RequiredArgsConstructor
public class LoginController {

    private final AccountService accountService;
    private final AuthenticationService authenticationService;

    @PostMapping("/authenticate")
    public ResponseEntity<ResponseDto> authenticationCustomer(@RequestBody RequestDto request) {
        return ResponseEntity.ok(authenticationService.userAuthentication(request));
    }

    @PostMapping("/account-created")
    public ResponseEntity<ResponseDto> accountCreated(@RequestBody RequestDto request) {
        ResponseDto response = accountService.createdAccount(request);
        if(response!=null) {
            return ResponseEntity.ok(response);
        }else {
            return ResponseEntity.notFound().build();
        }
    }
}
