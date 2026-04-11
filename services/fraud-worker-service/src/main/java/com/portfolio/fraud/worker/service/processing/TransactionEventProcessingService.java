package com.portfolio.fraud.worker.service.processing;

import com.portfolio.fraud.worker.dto.event.TransactionCreatedEvent;

public interface TransactionEventProcessingService {

    void processTransactionCreated(TransactionCreatedEvent event);
}