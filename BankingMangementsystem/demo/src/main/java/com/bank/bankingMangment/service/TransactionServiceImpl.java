package com.bank.bankingMangment.service;

import com.bank.bankingMangment.entity.Account;
import com.bank.bankingMangment.entity.Transaction;
import com.bank.bankingMangment.exception.InsufficientBalanceException;
import com.bank.bankingMangment.exception.InvalidRequestException;
import com.bank.bankingMangment.exception.ResourceNotFoundException;
import com.bank.bankingMangment.repository.AccountRepository;
import com.bank.bankingMangment.repository.TransactionRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {

    private final AccountRepository accountRepo;
    private final TransactionRepository txRepo;

    @Override
    @Transactional
    public String deposit(Long accNo, BigDecimal amount) {
        Account acc = accountRepo.findById(accNo)
                .orElseThrow(() -> new ResourceNotFoundException("Account not found"));

        acc.setBalance(acc.getBalance().add(amount));
        accountRepo.save(acc);

        txRepo.save(new Transaction(null, accNo, "CREDIT", amount, acc.getBalance(),
                Instant.now(), "Deposit"));

        return "Deposit successful";
    }

    @Override
    @Transactional
    public String withdraw(Long accNo, BigDecimal amount) {

        if (amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new InvalidRequestException("Amount must be greater than zero");
        }

        Account acc = accountRepo.findById(accNo)
                .orElseThrow(() -> new ResourceNotFoundException("Account not found"));

        if (acc.getBalance().compareTo(amount) < 0) {
            throw new InsufficientBalanceException("Not enough balance");
        }

        acc.setBalance(acc.getBalance().subtract(amount));
        accountRepo.save(acc);

        return "Withdraw successful";
    }


    @Override
    @Transactional
    public String transfer(Long fromAcc, Long toAcc, BigDecimal amount) {

        if (amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new InvalidRequestException("Amount must be greater than zero");
        }

        Account sender = accountRepo.findById(fromAcc)
                .orElseThrow(() -> new ResourceNotFoundException("Sender account not found"));

        Account receiver = accountRepo.findById(toAcc)
                .orElseThrow(() -> new ResourceNotFoundException("Receiver account not found"));

        if (sender.getBalance().compareTo(amount) < 0) {
            throw new InsufficientBalanceException("Insufficient balance");
        }

        // business logic for transferring money


        return "Transfer successful";
    }



    @Override
    public List<Transaction> history(Long accNo) {
        return txRepo.findByAccNoOrderByTimestampDesc(accNo, PageRequest.of(0, 50))
                .getContent();
    }
}
