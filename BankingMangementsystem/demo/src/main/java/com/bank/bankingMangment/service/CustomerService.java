package com.bank.bankingMangment.service;

import com.bank.bankingMangment.dto.CustomerRegisterRequest;
import com.bank.bankingMangment.entity.Customer;

public interface CustomerService {
    Customer register(Customer customer);
    String login(String email, String password);
    Customer getProfile(Long id);

    // added for DTO-based register used by controller
    Customer registerFromDto(CustomerRegisterRequest req);
}
