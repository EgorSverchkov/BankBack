package com.example.bankbackendapi.model.entity;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "client")
public class ClientEntity {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    private String surname;

    private String phoneNumber;

    private String passport;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "accountEntity",referencedColumnName = "id")
    private AccountEntity accountEntity;


}
