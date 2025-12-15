package com.bank.bankingMangment.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class WithdrawRequest {
    private Long accNo;
    private BigDecimal amount;
}
