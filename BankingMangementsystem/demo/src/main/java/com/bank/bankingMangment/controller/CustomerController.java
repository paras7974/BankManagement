package com.bank.bankingMangment.controller;

import com.bank.bankingMangment.dto.ApiResponse;
import com.bank.bankingMangment.dto.CustomerRegisterRequest;
import com.bank.bankingMangment.dto.CustomerResponse;
import com.bank.bankingMangment.dto.LoginRequest;
import com.bank.bankingMangment.entity.Customer;
import com.bank.bankingMangment.service.CustomerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @PostMapping("/register")
    public ResponseEntity<CustomerResponse> register(@RequestBody @Valid CustomerRegisterRequest req){
        Customer saved = customerService.registerFromDto(req); // service maps + encodes password
        CustomerResponse resp = new CustomerResponse(saved.getId(), saved.getName(), saved.getEmail(), saved.getPhone());
        return ResponseEntity.status(HttpStatus.CREATED).body(resp);
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse> login(@RequestBody @Valid LoginRequest req){
        String token = customerService.login(req.getEmail(), req.getPassword());
        return ResponseEntity.ok(new ApiResponse("OK", token));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerResponse> getProfile(@PathVariable Long id){
        Customer c = customerService.getProfile(id);
        return ResponseEntity.ok(new CustomerResponse(c.getId(), c.getName(), c.getEmail(), c.getPhone()));
    }
}
