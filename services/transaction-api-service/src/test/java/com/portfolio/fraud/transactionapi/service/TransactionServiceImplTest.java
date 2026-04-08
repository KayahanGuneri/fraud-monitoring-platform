package com.portfolio.fraud.transactionapi.service;

import com.portfolio.fraud.transactionapi.domain.Transaction;
import com.portfolio.fraud.transactionapi.dto.request.CreateTransactionRequest;
import com.portfolio.fraud.transactionapi.dto.response.TransactionResponse;
import com.portfolio.fraud.transactionapi.dto.response.TransactionSummaryResponse;
import com.portfolio.fraud.transactionapi.exception.TransactionNotFoundException;
import com.portfolio.fraud.transactionapi.messaging.TransactionEventPublisher;
import com.portfolio.fraud.transactionapi.repository.TransactionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TransactionServiceImplTest {

    @Mock
    private TransactionRepository transactionRepository;

    @Mock
    private TransactionEventPublisher transactionEventPublisher;

    @InjectMocks
    private TransactionServiceImpl transactionService;

    private UUID transactionId;
    private UUID userId;
    private Instant transactionTime;
    private Instant createdAt;

    @BeforeEach
    void setUp() {
        transactionId = UUID.fromString("9628dfa9-cd42-4f55-8e80-fbf5ce39fe3e");
        userId = UUID.fromString("550e8400-e29b-41d4-a716-446655440000");
        transactionTime = Instant.parse("2026-04-08T20:00:00Z");
        createdAt = Instant.parse("2026-04-08T20:38:42.058962Z");
    }

    @Test
    void createTransaction_shouldSaveTransactionAndPublishEvent() {
        CreateTransactionRequest request = CreateTransactionRequest.builder()
                .userId(userId)
                .amount(new BigDecimal("150.75"))
                .currency("USD")
                .merchant("Amazon")
                .country("Turkey")
                .city("Istanbul")
                .latitude(new BigDecimal("41.0082"))
                .longitude(new BigDecimal("28.9784"))
                .transactionTime(transactionTime)
                .build();

        Transaction savedTransaction = Transaction.builder()
                .id(transactionId)
                .userId(userId)
                .amount(new BigDecimal("150.75"))
                .currency("USD")
                .merchant("Amazon")
                .country("Turkey")
                .city("Istanbul")
                .latitude(new BigDecimal("41.0082"))
                .longitude(new BigDecimal("28.9784"))
                .transactionTime(transactionTime)
                .createdAt(createdAt)
                .build();

        when(transactionRepository.save(any(Transaction.class))).thenReturn(savedTransaction);

        TransactionResponse response = transactionService.createTransaction(request);

        assertNotNull(response);
        assertEquals(transactionId, response.getId());
        assertEquals(userId, response.getUserId());
        assertEquals(new BigDecimal("150.75"), response.getAmount());
        assertEquals("USD", response.getCurrency());
        assertEquals("Amazon", response.getMerchant());
        assertEquals("Turkey", response.getCountry());
        assertEquals("Istanbul", response.getCity());
        assertEquals(transactionTime, response.getTransactionTime());
        assertEquals(createdAt, response.getCreatedAt());

        ArgumentCaptor<Transaction> transactionCaptor = ArgumentCaptor.forClass(Transaction.class);
        verify(transactionRepository, times(1)).save(transactionCaptor.capture());
        verify(transactionEventPublisher, times(1)).publishTransactionCreated(savedTransaction);

        Transaction capturedTransaction = transactionCaptor.getValue();
        assertEquals(userId, capturedTransaction.getUserId());
        assertEquals(new BigDecimal("150.75"), capturedTransaction.getAmount());
        assertEquals("USD", capturedTransaction.getCurrency());
        assertEquals("Amazon", capturedTransaction.getMerchant());
    }

    @Test
    void getTransactionById_shouldReturnTransactionResponse_whenTransactionExists() {
        Transaction transaction = Transaction.builder()
                .id(transactionId)
                .userId(userId)
                .amount(new BigDecimal("150.75"))
                .currency("USD")
                .merchant("Amazon")
                .country("Turkey")
                .city("Istanbul")
                .latitude(new BigDecimal("41.0082"))
                .longitude(new BigDecimal("28.9784"))
                .transactionTime(transactionTime)
                .createdAt(createdAt)
                .build();

        when(transactionRepository.findById(transactionId)).thenReturn(Optional.of(transaction));

        TransactionResponse response = transactionService.getTransactionById(transactionId);

        assertNotNull(response);
        assertEquals(transactionId, response.getId());
        assertEquals(userId, response.getUserId());
        assertEquals("Amazon", response.getMerchant());
        verify(transactionRepository, times(1)).findById(transactionId);
    }

    @Test
    void getTransactionById_shouldThrowException_whenTransactionDoesNotExist() {
        when(transactionRepository.findById(transactionId)).thenReturn(Optional.empty());

        assertThrows(TransactionNotFoundException.class,
                () -> transactionService.getTransactionById(transactionId));

        verify(transactionRepository, times(1)).findById(transactionId);
        verifyNoInteractions(transactionEventPublisher);
    }

    @Test
    void getTransactionsByUserId_shouldReturnTransactionSummaries() {
        Transaction transaction1 = Transaction.builder()
                .id(transactionId)
                .userId(userId)
                .amount(new BigDecimal("150.75"))
                .currency("USD")
                .merchant("Amazon")
                .country("Turkey")
                .city("Istanbul")
                .transactionTime(transactionTime)
                .createdAt(createdAt)
                .build();

        Transaction transaction2 = Transaction.builder()
                .id(UUID.fromString("4622f4d6-378f-49e7-b6cf-d2725a3d550a"))
                .userId(userId)
                .amount(new BigDecimal("90.50"))
                .currency("EUR")
                .merchant("Nike")
                .country("Turkey")
                .city("Ankara")
                .transactionTime(Instant.parse("2026-04-08T21:00:00Z"))
                .createdAt(Instant.parse("2026-04-08T21:05:00Z"))
                .build();

        when(transactionRepository.findByUserIdOrderByTransactionTimeDesc(userId))
                .thenReturn(List.of(transaction1, transaction2));

        List<TransactionSummaryResponse> response = transactionService.getTransactionsByUserId(userId);

        assertNotNull(response);
        assertEquals(2, response.size());
        assertEquals("Amazon", response.get(0).getMerchant());
        assertEquals("Nike", response.get(1).getMerchant());
        verify(transactionRepository, times(1)).findByUserIdOrderByTransactionTimeDesc(userId);
    }
}