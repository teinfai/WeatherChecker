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
        String QUEUE_NAME = "registerUserQueue";
        dynamicQueueService.createQueueIfNotExists(QUEUE_NAME);
        rabbitTemplate.convertAndSend(QUEUE_NAME, user);
    }


    public void sendLoginSessionToQueue(String queueName, String sessionId) {
        rabbitTemplate.convertAndSend(queueName, sessionId);
    }

}
