package com.bank.bankingMangment.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class TransferRequest {
    private Long fromAcc;
    private Long toAcc;
    private BigDecimal amount;
}
