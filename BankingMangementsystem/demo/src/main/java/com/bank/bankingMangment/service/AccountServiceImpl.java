package com.bank.bankingMangment.service;

import com.bank.bankingMangment.entity.Account;
import com.bank.bankingMangment.exception.ResourceNotFoundException;
import com.bank.bankingMangment.repository.AccountRepository;
import com.bank.bankingMangment.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final CustomerRepository customerRepo;
    private final AccountRepository accountRepo;

    @Override
    public Account createAccount(Long customerId, String type) {

        customerRepo.findById(customerId)
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found"));

        Account account = new Account();
        account.setCustomerId(customerId);
        account.setType(type);
        account.setBalance(BigDecimal.ZERO);

        return accountRepo.save(account);
    }

    @Override
    public Account getAccount(Long accNo) {
        return accountRepo.findById(accNo)
                .orElseThrow(() -> new ResourceNotFoundException("Account not found"));
    }


    public BigDecimal getBalance(Long accNo) {
        return getAccount(accNo).getBalance();
    }
}
