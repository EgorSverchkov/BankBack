package com.example.bankbackendapi.model.dto;


import lombok.Data;

@Data
public class ClientDto {

    private Long id;

    private String name;

    private String surname;

    private String phoneNumber;

    private String passport;

}
