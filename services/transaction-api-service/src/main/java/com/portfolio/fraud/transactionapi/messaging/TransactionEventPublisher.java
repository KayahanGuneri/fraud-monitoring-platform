package com.portfolio.fraud.transactionapi.messaging;

import com.portfolio.fraud.transactionapi.domain.Transaction;

public interface TransactionEventPublisher {

    void publishTransactionCreated(Transaction transaction);
}