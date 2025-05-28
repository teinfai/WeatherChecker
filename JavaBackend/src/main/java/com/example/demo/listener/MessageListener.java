package com.example.demo.listener;

import com.example.demo.config.RabbitMQConfig;
import com.example.demo.entity.User;
import com.example.demo.service.UserService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MessageListener {

    @Autowired
    private UserService userService;    // now guaranteed non-null

    @RabbitListener(queues = RabbitMQConfig.EMAIL_QUEUE)
    public void receive(User user) {
        System.out.println("📥 Listener received: " + user.getName());
        userService.createUser(user);   // will now work without NPE
    }
}
