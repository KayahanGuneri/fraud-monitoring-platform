package com.portfolio.fraud.transactionapi.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

@Getter
@Builder
@AllArgsConstructor
public class TransactionSummaryResponse {

    private UUID id;
    private BigDecimal amount;
    private String currency;
    private String merchant;
    private String country;
    private String city;
    private Instant transactionTime;
    private Instant createdAt;
    private UUID userId;
}