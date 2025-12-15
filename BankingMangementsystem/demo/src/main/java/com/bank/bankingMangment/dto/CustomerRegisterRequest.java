package com.bank.bankingMangment.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

// dto/CustomerRegisterRequest.java
@Data
public class CustomerRegisterRequest {
    @NotBlank
    private String name;
    @Email
    @NotBlank private String email;
    @NotBlank @Size(min = 6) private String password;
    private String phone;
}

