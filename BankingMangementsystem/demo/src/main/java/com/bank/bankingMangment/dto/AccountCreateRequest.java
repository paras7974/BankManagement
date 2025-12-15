package com.bank.bankingMangment.dto;

import lombok.Data;

@Data
public class AccountCreateRequest {
    private Long customerId;
    private String type;
}
