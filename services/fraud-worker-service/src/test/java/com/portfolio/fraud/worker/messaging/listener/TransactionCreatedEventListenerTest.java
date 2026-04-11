package com.portfolio.fraud.worker.messaging.listener;

import com.portfolio.fraud.worker.dto.event.TransactionCreatedEvent;
import com.portfolio.fraud.worker.dto.event.TransactionCreatedPayload;
import com.portfolio.fraud.worker.messaging.WorkerMessagingProperties;
import com.portfolio.fraud.worker.service.processing.TransactionEventProcessingService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TransactionCreatedEventListenerTest {

    @Mock
    private TransactionEventProcessingService transactionEventProcessingService;

    @Mock
    private WorkerMessagingProperties properties;

    @InjectMocks
    private TransactionCreatedEventListener listener;

    @Test
    void shouldDelegateEventToProcessingService() {
        when(properties.getTransactionCreatedEventType()).thenReturn("transaction.created");

        TransactionCreatedEvent event = TransactionCreatedEvent.builder()
                .eventId(UUID.randomUUID())
                .eventType("transaction.created")
                .occurredAt(Instant.parse("2026-04-11T10:15:30Z"))
                .payload(
                        TransactionCreatedPayload.builder()
                                .transactionId(UUID.randomUUID())
                                .userId(UUID.randomUUID())
                                .amount(new BigDecimal("1250.50"))
                                .currency("TRY")
                                .merchant("ABC Market")
                                .country("Turkey")
                                .city("Istanbul")
                                .latitude(new BigDecimal("41.0082"))
                                .longitude(new BigDecimal("28.9784"))
                                .transactionTime(Instant.parse("2026-04-11T10:14:00Z"))
                                .createdAt(Instant.parse("2026-04-11T10:15:25Z"))
                                .build()
                )
                .build();

        listener.handleTransactionCreated(event);

        verify(transactionEventProcessingService).processTransactionCreated(event);
    }

    @Test
    void shouldIgnoreNullEvent() {
        listener.handleTransactionCreated(null);

        verifyNoInteractions(transactionEventProcessingService);
    }
}