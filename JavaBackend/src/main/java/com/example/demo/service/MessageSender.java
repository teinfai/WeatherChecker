package com.example.demo.service;

import com.example.demo.config.RabbitMQConfig;
import com.example.demo.entity.User;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MessageSender {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void send(User user) {
        rabbitTemplate.convertAndSend(RabbitMQConfig.EMAIL_QUEUE, user);
    }
}
