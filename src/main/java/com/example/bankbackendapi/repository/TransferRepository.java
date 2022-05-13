package com.example.bankbackendapi.repository;

import com.example.bankbackendapi.model.entity.TransferEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.Optional;

public interface TransferRepository extends JpaRepository<TransferEntity, Long> {
    Optional<TransferEntity> findByDateOfTransferAndId(LocalDateTime dateOfTransfer, Long id);
    Optional<TransferEntity> findByAccountNumberFromAndAccountNumberToAndTransferAmount(Long accountNumberFrom, Long accountNumberTo, Double transferAmount);
}