package com.portfolio.fraud.transactionapi.messaging;

import com.portfolio.fraud.transactionapi.domain.Transaction;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class NoOpTransactionEventPublisher implements TransactionEventPublisher {

    @Override
    public void publishTransactionCreated(Transaction transaction) {
        log.info("Phase 3 extension point - transaction created event not published yet. transactionId={}", transaction.getId());
    }
}