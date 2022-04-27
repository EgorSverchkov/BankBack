package com.example.bankbackendapi.service;

import com.example.bankbackendapi.model.dto.AccountDto;

public interface AccountService {
    String createAccount(AccountDto accountDto);
    String deleteAccount(Long accountNumber);
    String freezeAccount(Long accountNumber);
    String unfreezeAccount(Long accountNumber);
    AccountDto getAccount(Long accountNumber);
}
