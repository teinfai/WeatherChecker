package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class AuthLoginQueueStatusService {

    private static final String QUEUE_KEY = "auth_login_waiting_list";
    private static final String STATUS_KEY_PREFIX = "auth_login_status:";

    @Autowired private StringRedisTemplate redis;

    // ➌ enqueue + mark "waiting"
    public void enqueue(String sessionId) {
        redis.opsForList().rightPush(QUEUE_KEY, sessionId);
        redis.opsForHash().put(STATUS_KEY_PREFIX + sessionId, "status", "waiting");
    }

    public String getStatus(String sessionId) {
        Object s = redis.opsForHash().get(STATUS_KEY_PREFIX + sessionId, "status");
        return s == null ? "not_found" : s.toString();
    }

    public Long getPosition(String sessionId) {
        List<String> list = redis.opsForList().range(QUEUE_KEY, 0, -1);
        return list == null ? -1 : list.indexOf(sessionId) + 1L;
    }

    // ➍ finalise
    public void markReady(String sessionId) {
        redis.opsForHash().put(STATUS_KEY_PREFIX + sessionId, "status", "ready");
        redis.opsForList().remove(QUEUE_KEY, 1, sessionId);
    }
}

