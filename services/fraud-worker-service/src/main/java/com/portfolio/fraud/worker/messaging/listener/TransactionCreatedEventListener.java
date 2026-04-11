package com.portfolio.fraud.worker.messaging.listener;

import com.portfolio.fraud.worker.dto.event.TransactionCreatedEvent;
import com.portfolio.fraud.worker.messaging.WorkerMessagingProperties;
import com.portfolio.fraud.worker.service.processing.TransactionEventProcessingService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class TransactionCreatedEventListener {

    private final TransactionEventProcessingService transactionEventProcessingService;
    private final WorkerMessagingProperties properties;

    @RabbitListener(queues = "${app.messaging.transaction-created-queue}")
    public void handleTransactionCreated(TransactionCreatedEvent event) {
        if (event == null) {
            log.warn("Received null transaction created event.");
            return;
        }

        log.info(
                "Consumed transaction created event. eventId={}, eventType={}, expectedEventType={}",
                event.getEventId(),
                event.getEventType(),
                properties.getTransactionCreatedEventType()
        );

        transactionEventProcessingService.processTransactionCreated(event);
    }
}