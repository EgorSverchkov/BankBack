package com.example.bankbackendapi.model.entity;


import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "transfer")
public class TransferEntity {

    @Id
    @GeneratedValue
    private Long id;

    private Long accountNumberFrom;

    private Long accountNumberTo;

    private Double transferAmount;

    private LocalDateTime dateOfTransfer;
}
