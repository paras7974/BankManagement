package com.bank.bankingMangment.repository;

import com.bank.bankingMangment.entity.Transaction;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    Page<Transaction> findByAccNoOrderByTimestampDesc(Long accNo, Pageable p);
}
