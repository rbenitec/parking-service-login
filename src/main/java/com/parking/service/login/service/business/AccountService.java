package com.parking.service.login.service.business;

import com.parking.service.login.controller.dto.RequestAccountDto;
import com.parking.service.login.controller.dto.RequestDto;
import com.parking.service.login.controller.dto.ResponseDto;
import com.parking.service.login.entities.AccountEntity;

import java.util.List;
import java.util.Optional;

public interface AccountService {
    ResponseDto createdAccount(RequestAccountDto request);
    AccountEntity saveAccount(AccountEntity accountEntity);
    Optional<AccountEntity> getAccountByUsername(String username);
    void deleteAccount (Integer idAccount);
    List<AccountEntity> getAllAccount();
}
