package com.portfolio.fraud.worker.messaging;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "app.messaging")
public class WorkerMessagingProperties {

    private String exchange;
    private String transactionCreatedQueue;
    private String transactionCreatedRoutingKey;
    private String transactionCreatedEventType;
}