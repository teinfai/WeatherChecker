package com.example.demo.service;

import com.example.demo.entity.User;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MessageSender {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private DynamicQueueService dynamicQueueService;

    public void send(User user) {
        // Make sure queue exists (creates if not)
        String QUEUE_NAME = "registerUserQueue";
        dynamicQueueService.createQueueIfNotExists(QUEUE_NAME);
        // Send user as message
        rabbitTemplate.convertAndSend(QUEUE_NAME, user);
    }
}
