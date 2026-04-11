package com.portfolio.fraud.transactionapi.messaging;

import com.portfolio.fraud.transactionapi.domain.Transaction;
import com.portfolio.fraud.transactionapi.messaging.dto.TransactionCreatedEvent;
import com.portfolio.fraud.transactionapi.messaging.dto.TransactionCreatedPayload;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.UUID;

@Slf4j
@Component
@RequiredArgsConstructor
public class RabbitMqTransactionEventPublisher implements TransactionEventPublisher {

    private final RabbitTemplate rabbitTemplate;
    private final TransactionMessagingProperties properties;

    @Override
    public void publishTransactionCreated(Transaction transaction) {
        TransactionCreatedEvent event = buildTransactionCreatedEvent(transaction);

        rabbitTemplate.convertAndSend(
                properties.getExchange(),
                properties.getTransactionCreatedRoutingKey(),
                event
        );

        log.info(
                "Published transaction created event. eventId={}, transactionId={}, routingKey={}",
                event.getEventId(),
                transaction.getId(),
                properties.getTransactionCreatedRoutingKey()
        );
    }

    private TransactionCreatedEvent buildTransactionCreatedEvent(Transaction transaction) {
        return TransactionCreatedEvent.builder()
                .eventId(UUID.randomUUID())
                .eventType(properties.getTransactionCreatedEventType())
                .occurredAt(Instant.now())
                .payload(
                        TransactionCreatedPayload.builder()
                                .transactionId(transaction.getId())
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
                                .build()
                )
                .build();
    }
}