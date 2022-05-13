package com.example.bankbackendapi.model.dto;

import lombok.Data;

@Data
public class InformationForMakingTransferDto {

    private Long accountNumberFrom;

    private Long accountNumberTo;

    private Double transferAmount;
}
