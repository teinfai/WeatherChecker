package com.example.demo.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

@Configuration
public class RabbitMQConfig {
    public static final String EMAIL_QUEUE = "emailQueue";
    public static final String DELETE_USER_QUEUE = "deleteUserQueue";

    @Bean
    public Queue emailQueue() {
        return new Queue(EMAIL_QUEUE, true);
    }

    @Bean
    public Queue deleteUserQueue() {
        return new Queue(DELETE_USER_QUEUE, true);
    }

    @Bean
    public Jackson2JsonMessageConverter jacksonConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate template = new RabbitTemplate(connectionFactory);
        template.setMessageConverter(jacksonConverter());
        return template;
    }
}
