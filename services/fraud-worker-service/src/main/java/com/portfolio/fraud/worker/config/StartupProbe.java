package com.portfolio.fraud.worker.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class StartupProbe {

    private static final Logger log = LoggerFactory.getLogger(StartupProbe.class);

    @Bean
    public ApplicationRunner workerStartupLogger() {
        return args -> log.info("fraud-worker-service skeleton started successfully");
    }
}