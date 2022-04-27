package com.example.bankbackendapi.model.entity;


import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "account")
public class AccountEntity {

    @Id
    @GeneratedValue
    private Long id;

    private Long accountNumber;

    private Double balance;

    private Boolean isFrozen;


}
