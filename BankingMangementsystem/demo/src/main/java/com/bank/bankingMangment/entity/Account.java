package com.bank.bankingMangment.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

import java.math.BigDecimal;
import java.time.Instant;

@Entity
@Data
public class Account {
    @Id
    @GeneratedValue
    private Long accNo;
    private Long customerId;
    private String type; // SAVINGS/CURRENT
    private BigDecimal balance = BigDecimal.ZERO;
    private String status = "ACTIVE";
    private Instant createdAt = Instant.now();
}
