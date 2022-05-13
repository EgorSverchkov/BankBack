package com.example.bankbackendapi.model.dto;


import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TransferDto {
    private Long id;

    private Long accountNumberFrom;

    private Long accountNumberTo;

    private Double transferAmount;

    private LocalDateTime dateOfTransfer;

}
