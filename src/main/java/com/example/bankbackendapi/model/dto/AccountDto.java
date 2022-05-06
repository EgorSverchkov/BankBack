package com.example.bankbackendapi.model.dto;


import lombok.Data;

@Data
public class AccountDto {
    private Long accountNumber;
    private Long clientId;
    private Double balance;
    private Boolean isFrozen;
}
