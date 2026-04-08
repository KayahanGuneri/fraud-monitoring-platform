package com.portfolio.fraud.transactionapi.repository;

import com.portfolio.fraud.transactionapi.domain.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface TransactionRepository extends JpaRepository<Transaction, UUID> {

    List<Transaction> findByUserIdOrderByTransactionTimeDesc(UUID userId);
}