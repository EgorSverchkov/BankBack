package com.example.bankbackendapi.service;

import com.example.bankbackendapi.model.dto.TransferDto;
import org.springframework.web.bind.annotation.ResponseStatus;

public interface TransferService {
    String makeTranser(Long accountNumberFrom, Long accountNumberTo, Double transferAmount);
    TransferDto giveInfoAboutTransfer(Long accountNumberFrom, Long accountNumberTo, Double transferAmount);

}