package com.parking.service.login.service.business;

import com.parking.service.login.controller.dto.RequestDto;
import com.parking.service.login.controller.dto.ResponseDto;
import com.parking.service.login.entities.Account;

import java.util.List;

public interface AccountService {
    ResponseDto createdAccount(RequestDto request);
    Account saveAccount(Account account);
    Account getAccountByUsername(String username);
    void deleteAccountByUsername(String username);
    List<Account> getAllAccount();
}
