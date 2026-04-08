package com.portfolio.fraud.transactionapi.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.portfolio.fraud.transactionapi.dto.request.CreateTransactionRequest;
import com.portfolio.fraud.transactionapi.dto.response.TransactionResponse;
import com.portfolio.fraud.transactionapi.dto.response.TransactionSummaryResponse;
import com.portfolio.fraud.transactionapi.exception.GlobalExceptionHandler;
import com.portfolio.fraud.transactionapi.exception.TransactionNotFoundException;
import com.portfolio.fraud.transactionapi.service.TransactionService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(TransactionController.class)
@Import(GlobalExceptionHandler.class)
class TransactionControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private TransactionService transactionService;

    @Test
    @DisplayName("POST /api/v1/transactions should return 201 when request is valid")
    void createTransaction_shouldReturnCreated() throws Exception {
        UUID transactionId = UUID.fromString("9628dfa9-cd42-4f55-8e80-fbf5ce39fe3e");
        UUID userId = UUID.fromString("550e8400-e29b-41d4-a716-446655440000");
        Instant transactionTime = Instant.parse("2026-04-08T20:00:00Z");
        Instant createdAt = Instant.parse("2026-04-08T20:38:42.058962Z");

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

        TransactionResponse response = TransactionResponse.builder()
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

        when(transactionService.createTransaction(any(CreateTransactionRequest.class))).thenReturn(response);

        mockMvc.perform(post("/api/v1/transactions")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(transactionId.toString()))
                .andExpect(jsonPath("$.userId").value(userId.toString()))
                .andExpect(jsonPath("$.currency").value("USD"))
                .andExpect(jsonPath("$.merchant").value("Amazon"));
    }

    @Test
    @DisplayName("POST /api/v1/transactions should return 400 when request is invalid")
    void createTransaction_shouldReturnBadRequest_whenValidationFails() throws Exception {
        String invalidRequest = """
                {
                  "userId": null,
                  "amount": -5,
                  "currency": "us",
                  "merchant": "",
                  "country": "",
                  "city": "",
                  "latitude": 200,
                  "longitude": 300,
                  "transactionTime": "2030-01-01T10:00:00Z"
                }
                """;

        mockMvc.perform(post("/api/v1/transactions")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(invalidRequest))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.title").value("Validation Failed"))
                .andExpect(jsonPath("$.errors.userId").value("userId is required"))
                .andExpect(jsonPath("$.errors.amount").value("amount must be greater than 0"))
                .andExpect(jsonPath("$.errors.currency").value("currency must be a 3-letter uppercase ISO code"));
    }

    @Test
    @DisplayName("GET /api/v1/transactions/{id} should return transaction when found")
    void getTransactionById_shouldReturnOk() throws Exception {
        UUID transactionId = UUID.fromString("9628dfa9-cd42-4f55-8e80-fbf5ce39fe3e");
        UUID userId = UUID.fromString("550e8400-e29b-41d4-a716-446655440000");

        TransactionResponse response = TransactionResponse.builder()
                .id(transactionId)
                .userId(userId)
                .amount(new BigDecimal("150.75"))
                .currency("USD")
                .merchant("Amazon")
                .country("Turkey")
                .city("Istanbul")
                .latitude(new BigDecimal("41.0082"))
                .longitude(new BigDecimal("28.9784"))
                .transactionTime(Instant.parse("2026-04-08T20:00:00Z"))
                .createdAt(Instant.parse("2026-04-08T20:38:42.058962Z"))
                .build();

        when(transactionService.getTransactionById(transactionId)).thenReturn(response);

        mockMvc.perform(get("/api/v1/transactions/{id}", transactionId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(transactionId.toString()))
                .andExpect(jsonPath("$.merchant").value("Amazon"));
    }

    @Test
    @DisplayName("GET /api/v1/transactions/{id} should return 404 when transaction is not found")
    void getTransactionById_shouldReturnNotFound() throws Exception {
        UUID transactionId = UUID.fromString("11111111-1111-1111-1111-111111111111");

        when(transactionService.getTransactionById(transactionId))
                .thenThrow(new TransactionNotFoundException(transactionId));

        mockMvc.perform(get("/api/v1/transactions/{id}", transactionId))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.title").value("Transaction Not Found"))
                .andExpect(jsonPath("$.detail").value("Transaction not found with id: " + transactionId));
    }

    @Test
    @DisplayName("GET /api/v1/users/{userId}/transactions should return transaction summaries")
    void getTransactionsByUserId_shouldReturnOk() throws Exception {
        UUID userId = UUID.fromString("550e8400-e29b-41d4-a716-446655440000");

        List<TransactionSummaryResponse> response = List.of(
                TransactionSummaryResponse.builder()
                        .id(UUID.fromString("9628dfa9-cd42-4f55-8e80-fbf5ce39fe3e"))
                        .userId(userId)
                        .amount(new BigDecimal("150.75"))
                        .currency("USD")
                        .merchant("Amazon")
                        .country("Turkey")
                        .city("Istanbul")
                        .transactionTime(Instant.parse("2026-04-08T20:00:00Z"))
                        .createdAt(Instant.parse("2026-04-08T20:38:42.058962Z"))
                        .build(),
                TransactionSummaryResponse.builder()
                        .id(UUID.fromString("4622f4d6-378f-49e7-b6cf-d2725a3d550a"))
                        .userId(userId)
                        .amount(new BigDecimal("90.50"))
                        .currency("EUR")
                        .merchant("Nike")
                        .country("Turkey")
                        .city("Ankara")
                        .transactionTime(Instant.parse("2026-04-08T21:00:00Z"))
                        .createdAt(Instant.parse("2026-04-08T21:05:00Z"))
                        .build()
        );

        when(transactionService.getTransactionsByUserId(userId)).thenReturn(response);

        mockMvc.perform(get("/api/v1/users/{userId}/transactions", userId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].merchant").value("Amazon"))
                .andExpect(jsonPath("$[1].merchant").value("Nike"));
    }
}