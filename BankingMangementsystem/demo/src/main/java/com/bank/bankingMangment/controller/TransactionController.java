package com.bank.bankingMangment.controller;

import com.bank.bankingMangment.dto.ApiResponse;
import com.bank.bankingMangment.dto.DepositRequest;
import com.bank.bankingMangment.dto.TransferRequest;
import com.bank.bankingMangment.dto.WithdrawRequest;
import com.bank.bankingMangment.entity.Transaction;
import com.bank.bankingMangment.service.TransactionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/transactions")
@RequiredArgsConstructor
public class TransactionController {

    private final TransactionService txService;

    @PostMapping("/deposit")
    public ResponseEntity<ApiResponse> deposit(@RequestBody @Valid DepositRequest req){
        String msg = txService.deposit(req.getAccNo(), req.getAmount());
        return ResponseEntity.ok(new ApiResponse());
    }

    @PostMapping("/withdraw")
    public ResponseEntity<ApiResponse> withdraw(@RequestBody @Valid WithdrawRequest req){
        String msg = txService.withdraw(req.getAccNo(), req.getAmount());
        return ResponseEntity.ok(new ApiResponse());
    }

    @PostMapping("/transfer")
    public ResponseEntity<ApiResponse> transfer(@RequestBody @Valid TransferRequest req){
        String msg = txService.transfer(req.getFromAcc(), req.getToAcc(), req.getAmount());
        return ResponseEntity.ok(new ApiResponse());
    }

    @GetMapping("/history/{accNo}")
    public ResponseEntity<List<Transaction>> history(@PathVariable Long accNo,
                                                     @RequestParam(defaultValue = "0") int page,
                                                     @RequestParam(defaultValue = "20") int size){
        List<Transaction> list = txService.history(accNo); // service already returns page 0..50; adapt if needed
        return ResponseEntity.ok(list);
    }
}
