package com.example.demo.controller;
import com.example.demo.service.AuthLoginQueueStatusService;
import com.example.demo.service.DynamicQueueService;


import com.example.demo.dto.LoginRequestDTO;
import com.example.demo.dto.TokenResponseDTO;
import com.example.demo.service.AuthService;
import com.example.demo.service.MessageSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @Autowired
    private DynamicQueueService dynamicQueueService;


    @Autowired
    private AuthLoginQueueStatusService authLoginQueueStatusService;

    @Autowired
    private MessageSender messageSender;


//    @PostMapping("/logins")
//    public ResponseEntity<TokenResponseDTO> logins(@RequestBody LoginRequestDTO request) {
//        TokenResponseDTO response = authService.login(request);
//        return ResponseEntity.ok(response);
//    }

    @PostMapping("/login-queue")
    public ResponseEntity<String> loginQueue(@RequestBody LoginRequestDTO request) {
        String sessionId = UUID.randomUUID().toString();
        // Optionally store LoginRequestDTO in Redis for use in listener
        authLoginQueueStatusService.enqueue(sessionId);
        messageSender.sendLoginSessionToQueue("authLoginUserQueue", sessionId);
        return ResponseEntity.ok(sessionId);
    }

    // Poll login status
    @GetMapping("/login-status/{sessionId}")
    public ResponseEntity<String> loginStatus(@PathVariable String sessionId) {
        String status = authLoginQueueStatusService.getStatus(sessionId);
        Long pos = authLoginQueueStatusService.getPosition(sessionId);
        return ResponseEntity.ok(String.format("Status: %s, Position: %d", status, pos));
    }

    // Original login endpoint
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequestDTO request) {
        return ResponseEntity.ok(authService.login(request));
    }
}
