package com.portfolio.fraud.transactionapi.service;

import com.portfolio.fraud.transactionapi.dto.request.CreateTransactionRequest;
import com.portfolio.fraud.transactionapi.dto.response.TransactionResponse;
import com.portfolio.fraud.transactionapi.dto.response.TransactionSummaryResponse;

import java.util.List;
import java.util.UUID;

public interface TransactionService {

    TransactionResponse createTransaction(CreateTransactionRequest request);

    TransactionResponse getTransactionById(UUID id);

    List<TransactionSummaryResponse> getTransactionsByUserId(UUID userId);
}