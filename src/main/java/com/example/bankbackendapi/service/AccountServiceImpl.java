package com.example.bankbackendapi.service;

import com.example.bankbackendapi.exception.BadRequestException;
import com.example.bankbackendapi.model.dto.AccountDto;
import com.example.bankbackendapi.model.entity.AccountEntity;
import com.example.bankbackendapi.repository.AccountRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;

    private final Double STARTING_BALANCE = 0D;

    private final Boolean INITIAL_STATE = false;

    public AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }


    @Override
    public String createAccount(AccountDto accountDto) {
        if (checkAccount(accountDto.getAccountNumber())) {
            throw new BadRequestException("Already have");
        }

        accountRepository.save(AccountEntity.builder()
                .accountNumber(accountDto.getAccountNumber())
                .balance(STARTING_BALANCE)
                .isFrozen(INITIAL_STATE)
                .build());

        return "Account created";
    }

    @Override
    @Transactional
    public String deleteAccount(Long accountNumber) {

        if (checkAccount(accountNumber)) {
            throw new BadRequestException("Account does not exist");
        }
        accountRepository.deleteByAccountNumber(accountNumber);

        return "Account deleted";
    }

    @Override
    public String freezeAccount(Long accountNumber) {
        if (checkAccount(accountNumber)) {
            throw new BadRequestException("Account does not exist");
        }
        AccountEntity byAccountNumber = accountRepository.getByAccountNumber(accountNumber);
        byAccountNumber.setIsFrozen(true);
        accountRepository.save(byAccountNumber);
        return "Account frozen";
    }

    @Override
    public String unfreezeAccount(Long accountNumber) {
        if (checkAccount(accountNumber)) {
            throw new BadRequestException("Account does not exist");
        }
        AccountEntity byAccountNumber = accountRepository.getByAccountNumber(accountNumber);
        byAccountNumber.setIsFrozen(false);
        accountRepository.save(byAccountNumber);
        return "Account unfrozen";
    }

    @Override
    public AccountDto getAccount(Long accountNumber) {
        if (checkAccount(accountNumber)) {
            throw new BadRequestException("Account does not exist");
        }
        AccountEntity byAccountNumber = accountRepository.getByAccountNumber(accountNumber);
        AccountDto accountDto = new AccountDto();
        accountDto.setClientId(byAccountNumber.getId());
        accountDto.setAccountNumber(byAccountNumber.getAccountNumber());
        accountDto.setIsFrozen(byAccountNumber.getIsFrozen());
        accountDto.setBalance(byAccountNumber.getBalance());
        return accountDto;
    }

    public String withdrawMoneyFromAccountBalance(Long accountNumber, Double amount) {
        if (checkAccount(accountNumber)) {
            throw new BadRequestException("Account does not exist");
        }
        AccountEntity accountEntity = accountRepository.getByAccountNumber(accountNumber);
        if (accountEntity.getBalance() < amount) {
            return "There is not enough money on the account";
        }
        Double balanceAfter = accountEntity.getBalance() - amount;
        accountEntity.setBalance(balanceAfter);
        accountRepository.save(accountEntity);
        return "Funds withdrawn";
    }

    @Override
    public String addMoneyOnAccountBalance(Long accountNumber, Double amount) {
        if (checkAccount(accountNumber)) {
            throw new BadRequestException("Account does not exist");
        }
        AccountEntity accountEntity = accountRepository.getByAccountNumber(accountNumber);
        Double balanceAfter = accountEntity.getBalance() + amount;
        accountEntity.setBalance(balanceAfter);
        accountRepository.save(accountEntity);
        return "Funds added";

    }


    private boolean checkAccount(Long accountNumber) {
        Optional<AccountEntity> accountEntity = accountRepository.findById(accountNumber);
        if (accountEntity.isEmpty()) {
            return false;
        }
        return true;
    }
}
