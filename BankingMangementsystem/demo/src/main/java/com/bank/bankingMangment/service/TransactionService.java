package com.bank.bankingMangment.service;

import com.bank.bankingMangment.entity.Transaction;

import java.math.BigDecimal;
import java.util.List;

public interface TransactionService {
    String deposit(Long accNo, BigDecimal amount);
    String withdraw(Long accNo, BigDecimal amount);
    String transfer(Long fromAcc, Long toAcc, BigDecimal amount);
    List<Transaction> history(Long accNo);
}
