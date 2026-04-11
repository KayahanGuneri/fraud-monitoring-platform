package com.portfolio.fraud.worker.dto.event;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TransactionCreatedPayload {

    private UUID transactionId;
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