package com.portfolio.fraud.worker.service.processing;

import com.portfolio.fraud.worker.dto.event.TransactionCreatedEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class TransactionEventProcessingServiceImpl implements TransactionEventProcessingService {

    @Override
    public void processTransactionCreated(TransactionCreatedEvent event) {
        if (event == null || event.getPayload() == null) {
            log.warn("Received empty transaction created event or payload.");
            return;
        }

        log.info(
                "Processing transaction created event. eventId={}, transactionId={}, userId={}, amount={}, currency={}",
                event.getEventId(),
                event.getPayload().getTransactionId(),
                event.getPayload().getUserId(),
                event.getPayload().getAmount(),
                event.getPayload().getCurrency()
        );

        log.info(
                "Phase 3 placeholder fraud pipeline executed. transactionId={}, merchant={}, location={},{}",
                event.getPayload().getTransactionId(),
                event.getPayload().getMerchant(),
                event.getPayload().getCountry(),
                event.getPayload().getCity()
        );
    }
}