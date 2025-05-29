package com.example.demo.listener;

import com.example.demo.config.RabbitMQConfig;
import com.example.demo.entity.User;
import com.example.demo.service.AuthLoginQueueStatusService;
import com.example.demo.service.UserService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MessageListener {

    @Autowired
    private UserService userService;    // now guaranteed non-null

    @Autowired
    private AuthLoginQueueStatusService authLoginQueueStatusService;

    @RabbitListener(queues = "registerUserQueue")
    public void handleUserRegistration(User user) {
        userService.createUser(user);   // will now work without NPE
    }

    @RabbitListener(queues = "authLoginUserQueue")
    public void handleLogin(String sessionId) {
        // TODO: (Optional) Fetch login request details from Redis (if you store them)
        // Simulate login processing
        System.out.println("Processing login for: " + sessionId);
        try { Thread.sleep(30000); } catch (Exception ignored) {}
        // After processing, mark status as ready
        authLoginQueueStatusService.markReady(sessionId);
    }
}
