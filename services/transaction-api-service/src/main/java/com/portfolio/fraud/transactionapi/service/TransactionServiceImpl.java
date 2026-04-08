package com.portfolio.fraud.transactionapi.service;

import com.portfolio.fraud.transactionapi.domain.Transaction;
import com.portfolio.fraud.transactionapi.dto.request.CreateTransactionRequest;
import com.portfolio.fraud.transactionapi.dto.response.TransactionResponse;
import com.portfolio.fraud.transactionapi.dto.response.TransactionSummaryResponse;
import com.portfolio.fraud.transactionapi.exception.TransactionNotFoundException;
import com.portfolio.fraud.transactionapi.messaging.TransactionEventPublisher;
import com.portfolio.fraud.transactionapi.repository.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {

    private final TransactionRepository transactionRepository;
    private final TransactionEventPublisher transactionEventPublisher;

    @Override
    public TransactionResponse createTransaction(CreateTransactionRequest request) {
        Transaction transaction = Transaction.builder()
                .userId(request.getUserId())
                .amount(request.getAmount())
                .currency(request.getCurrency())
                .merchant(request.getMerchant())
                .country(request.getCountry())
                .city(request.getCity())
                .latitude(request.getLatitude())
                .longitude(request.getLongitude())
                .transactionTime(request.getTransactionTime())
                .build();

        Transaction savedTransaction = transactionRepository.save(transaction);

        transactionEventPublisher.publishTransactionCreated(savedTransaction);

        return mapToTransactionResponse(savedTransaction);
    }

    @Override
    public TransactionResponse getTransactionById(UUID id) {
        Transaction transaction = transactionRepository.findById(id)
                .orElseThrow(() -> new TransactionNotFoundException(id));

        return mapToTransactionResponse(transaction);
    }

    @Override
    public List<TransactionSummaryResponse> getTransactionsByUserId(UUID userId) {
        return transactionRepository.findByUserIdOrderByTransactionTimeDesc(userId)
                .stream()
                .map(this::mapToTransactionSummaryResponse)
                .toList();
    }

    private TransactionResponse mapToTransactionResponse(Transaction transaction) {
        return TransactionResponse.builder()
                .id(transaction.getId())
                .userId(transaction.getUserId())
                .amount(transaction.getAmount())
                .currency(transaction.getCurrency())
                .merchant(transaction.getMerchant())
                .country(transaction.getCountry())
                .city(transaction.getCity())
                .latitude(transaction.getLatitude())
                .longitude(transaction.getLongitude())
                .transactionTime(transaction.getTransactionTime())
                .createdAt(transaction.getCreatedAt())
                .build();
    }

    private TransactionSummaryResponse mapToTransactionSummaryResponse(Transaction transaction) {
        return TransactionSummaryResponse.builder()
                .id(transaction.getId())
                .userId(transaction.getUserId())
                .amount(transaction.getAmount())
                .currency(transaction.getCurrency())
                .merchant(transaction.getMerchant())
                .country(transaction.getCountry())
                .city(transaction.getCity())
                .transactionTime(transaction.getTransactionTime())
                .createdAt(transaction.getCreatedAt())
                .build();
    }
}