package com.bank.bankingMangment.service;

import com.bank.bankingMangment.entity.Account;

import java.math.BigDecimal;

public interface AccountService {
    Account createAccount(Long customerId, String type);
    Account getAccount(Long accNo);
    BigDecimal getBalance(Long accNo);
}
