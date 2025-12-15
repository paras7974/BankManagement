package com.bank.bankingMangment.controller;

import com.bank.bankingMangment.dto.AccountCreateRequest;
import com.bank.bankingMangment.entity.Account;
import com.bank.bankingMangment.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Map;

@RestController
@RequestMapping("/api/accounts")
@RequiredArgsConstructor
public class AccountController {

      final AccountService accountService;

   /* @PostMapping("/create")
    public ResponseEntity<Account> create(@RequestParam Long customerId,
                                          @RequestParam String type){
        Account acc = accountService.createAccount(customerId, type);
        return ResponseEntity.status(HttpStatus.CREATED).body(acc);
    }*/
   @PostMapping("/create")
   public ResponseEntity<Account> create(@RequestBody AccountCreateRequest request) {
       Account acc = accountService.createAccount(
               request.getCustomerId(),
               request.getType()
       );
       return ResponseEntity.status(HttpStatus.CREATED).body(acc);
   }


    @GetMapping("/{accNo}")
    public ResponseEntity<Account> getAccount(@PathVariable Long accNo){
        return ResponseEntity.ok(accountService.getAccount(accNo));
    }

    @GetMapping("/{accNo}/balance")
    public ResponseEntity<Map<String, Object>> getBalance(@PathVariable Long accNo){
        BigDecimal bal = accountService.getBalance(accNo);
        return ResponseEntity.ok(Map.of("accNo", accNo, "balance", bal));
    }
}
