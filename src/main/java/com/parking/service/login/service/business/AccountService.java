package com.parking.service.login.service.business;

import com.parking.service.login.controller.dto.RequestDto;
import com.parking.service.login.controller.dto.ResponseDto;
import com.parking.service.login.entities.AccountEntity;

import java.util.List;

public interface AccountService {
    ResponseDto createdAccount(RequestDto request);
    AccountEntity saveAccount(AccountEntity accountEntity);
    AccountEntity getAccountByUsername(String username);
    void deleteAccountByUsername(String username);
    List<AccountEntity> getAllAccount();
}
