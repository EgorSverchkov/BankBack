package com.example.bankbackendapi.repository;

import com.example.bankbackendapi.model.entity.AccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<AccountEntity,Long> {

    void deleteByAccountNumber(Long accountNumber);

    AccountEntity getByAccountNumber(Long accountNumber);
}
