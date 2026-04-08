package com.portfolio.fraud.transactionapi.controller;

import com.portfolio.fraud.transactionapi.dto.request.CreateTransactionRequest;
import com.portfolio.fraud.transactionapi.dto.response.TransactionResponse;
import com.portfolio.fraud.transactionapi.dto.response.TransactionSummaryResponse;
import com.portfolio.fraud.transactionapi.service.TransactionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class TransactionController {

    private final TransactionService transactionService;

    @PostMapping("/transactions")
    @ResponseStatus(HttpStatus.CREATED)
    public TransactionResponse createTransaction(@Valid @RequestBody CreateTransactionRequest request) {
        return transactionService.createTransaction(request);
    }

    @GetMapping("/transactions/{id}")
    public TransactionResponse getTransactionById(@PathVariable UUID id) {
        return transactionService.getTransactionById(id);
    }

    @GetMapping("/users/{userId}/transactions")
    public List<TransactionSummaryResponse> getTransactionsByUserId(@PathVariable UUID userId) {
        return transactionService.getTransactionsByUserId(userId);
    }
}