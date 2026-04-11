package com.portfolio.fraud.transactionapi.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.boot.autoconfigure.amqp.RabbitTemplateCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMqConfig {

    @Bean
    public DirectExchange transactionExchange(com.portfolio.fraud.transactionapi.messaging.TransactionMessagingProperties properties) {
        return new DirectExchange(properties.getExchange(), true, false);
    }

    @Bean
    public Queue transactionCreatedQueue(com.portfolio.fraud.transactionapi.messaging.TransactionMessagingProperties properties) {
        return new Queue(properties.getTransactionCreatedQueue(), true);
    }

    @Bean
    public Binding transactionCreatedBinding(
            Queue transactionCreatedQueue,
            DirectExchange transactionExchange,
            com.portfolio.fraud.transactionapi.messaging.TransactionMessagingProperties properties
    ) {
        return BindingBuilder
                .bind(transactionCreatedQueue)
                .to(transactionExchange)
                .with(properties.getTransactionCreatedRoutingKey());
    }

    @Bean
    public MessageConverter jacksonMessageConverter(ObjectMapper objectMapper) {
        return new Jackson2JsonMessageConverter(objectMapper);
    }

    @Bean
    public RabbitTemplateCustomizer rabbitTemplateCustomizer(MessageConverter messageConverter) {
        return rabbitTemplate -> rabbitTemplate.setMessageConverter(messageConverter);
    }
}