package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class AuthLoginQueueStatusService {
    private static final String QUEUE_KEY = "auth_login_waiting_list";

    @Autowired
    private StringRedisTemplate redisTemplate;

    public void enqueue(String sessionId) {
        redisTemplate.opsForList().rightPush(QUEUE_KEY, sessionId);
        redisTemplate.opsForHash().put("auth_login_status:" + sessionId, "status", "waiting");
    }

    public String getStatus(String sessionId) {
        String status = (String) redisTemplate.opsForHash().get("auth_login_status:" + sessionId, "status");
        return status == null ? "not_found" : status;
    }

    public Long getPosition(String sessionId) {
        List<String> list = redisTemplate.opsForList().range(QUEUE_KEY, 0, -1);
        return list == null ? -1 : list.indexOf(sessionId) + 1L;
    }

    public void markReady(String sessionId) {
        redisTemplate.opsForHash().put("auth_login_status:" + sessionId, "status", "ready");
        redisTemplate.opsForList().remove(QUEUE_KEY, 1, sessionId);
    }
}
