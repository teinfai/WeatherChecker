package com.example.demo.service;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.entity.User;

@Service
public class MessageSender {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private DynamicQueueService dynamicQueueService;

    public void sendLoginSessionToQueue(String queueName, String sessionId) {
        dynamicQueueService.createQueueIfNotExists(queueName);
        rabbitTemplate.convertAndSend(queueName, sessionId);
    }

    // existing registerUserQueue sender stays unchanged
    public void send(User user) {
        String q = "registerUserQueue";
        dynamicQueueService.createQueueIfNotExists(q);
        rabbitTemplate.convertAndSend(q, user);
    }
}
