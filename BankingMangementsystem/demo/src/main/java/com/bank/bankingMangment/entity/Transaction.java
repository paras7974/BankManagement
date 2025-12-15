package com.bank.bankingMangment.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.Instant;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Transaction {

    @Id
    @GeneratedValue
    private Long id;

    private Long accNo;
    private String type;               // DEBIT / CREDIT
    private BigDecimal amount;
    private BigDecimal closingBalance;
    private Instant timestamp;
    private String description;
}
