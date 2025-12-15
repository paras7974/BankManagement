package com.bank.bankingMangment.service;

import com.bank.bankingMangment.config.JwtUtil;
import com.bank.bankingMangment.dto.CustomerRegisterRequest;
import com.bank.bankingMangment.entity.Customer;
import com.bank.bankingMangment.exception.ResourceNotFoundException;
import com.bank.bankingMangment.repository.CustomerRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepo;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    @Override
    public Customer register(Customer c) {
        c.setPasswordHash(passwordEncoder.encode(c.getPasswordHash()));
        return customerRepo.save(c);
    }

    @Override
    public Customer registerFromDto(CustomerRegisterRequest req) {
        Customer c = new Customer();
        c.setName(req.getName());
        c.setEmail(req.getEmail());
        c.setPhone(req.getPhone());
        c.setPasswordHash(passwordEncoder.encode(req.getPassword()));
        // createdAt will be set by entity default
        return customerRepo.save(c);
    }

    @Override
    public String login(String email, String password) {
        Customer c = customerRepo.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found"));

        if (!passwordEncoder.matches(password, c.getPasswordHash())) {
            throw new IllegalArgumentException("Invalid password");
        }

        return jwtUtil.generateToken(email);
    }

    @Override
    public Customer getProfile(Long id) {
        return customerRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Profile not found"));
    }
}
