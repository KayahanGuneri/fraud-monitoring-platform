package com.portfolio.fraud.worker.service.processing;

import com.portfolio.fraud.worker.dto.event.TransactionCreatedEvent;
import com.portfolio.fraud.worker.dto.event.TransactionCreatedPayload;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThatCode;

class TransactionEventProcessingServiceImplTest {

    private final TransactionEventProcessingServiceImpl service = new TransactionEventProcessingServiceImpl();

    @Test
    void shouldNotThrowWhenEventIsNull() {
        assertThatCode(() -> service.processTransactionCreated(null))
                .doesNotThrowAnyException();
    }

    @Test
    void shouldNotThrowWhenPayloadIsNull() {
        TransactionCreatedEvent event = TransactionCreatedEvent.builder()
                .eventId(UUID.randomUUID())
                .eventType("transaction.created")
                .occurredAt(Instant.now())
                .payload(null)
                .build();

        assertThatCode(() -> service.processTransactionCreated(event))
                .doesNotThrowAnyException();
    }

    @Test
    void shouldProcessValidEventWithoutThrowing() {
        TransactionCreatedEvent event = TransactionCreatedEvent.builder()
                .eventId(UUID.randomUUID())
                .eventType("transaction.created")
                .occurredAt(Instant.now())
                .payload(
                        TransactionCreatedPayload.builder()
                                .transactionId(UUID.randomUUID())
                                .userId(UUID.randomUUID())
                                .amount(new BigDecimal("500.00"))
                                .currency("TRY")
                                .merchant("Test Merchant")
                                .country("Turkey")
                                .city("Ankara")
                                .latitude(new BigDecimal("39.9334"))
                                .longitude(new BigDecimal("32.8597"))
                                .transactionTime(Instant.parse("2026-04-11T10:14:00Z"))
                                .createdAt(Instant.parse("2026-04-11T10:15:25Z"))
                                .build()
                )
                .build();

        assertThatCode(() -> service.processTransactionCreated(event))
                .doesNotThrowAnyException();
    }
}