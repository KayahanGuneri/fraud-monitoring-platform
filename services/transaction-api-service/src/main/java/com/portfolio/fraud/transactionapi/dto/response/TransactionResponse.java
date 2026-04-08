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
public class TransactionResponse {

    private UUID id;
    private UUID userId;
    private BigDecimal amount;
    private String currency;
    private String merchant;
    private String country;
    private String city;
    private BigDecimal latitude;
    private BigDecimal longitude;
    private Instant transactionTime;
    private Instant createdAt;
}
