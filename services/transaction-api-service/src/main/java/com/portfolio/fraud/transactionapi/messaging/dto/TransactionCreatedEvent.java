package com.portfolio.fraud.transactionapi.messaging.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TransactionCreatedEvent {

    private UUID eventId;
    private String eventType;
    private Instant occurredAt;
    private TransactionCreatedPayload payload;
}