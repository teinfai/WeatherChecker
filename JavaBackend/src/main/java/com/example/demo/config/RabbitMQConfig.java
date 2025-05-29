package com.example.demo.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    // ➊ already present
    @Bean
    public Queue loginStatusQueue() {
        return new Queue("loginStatusQueue", true);
    }

    // ➋ add missing queues
    @Bean
    public Queue registerUserQueue() {
        return new Queue("registerUserQueue", true);
    }

    @Bean
    public Queue authLoginUserQueue() {
        return new Queue("authLoginUserQueue", true);
    }
}
