package com.example.demo.service;

import com.example.demo.dao.AuthServiceDao;
import com.example.demo.dto.LoginRequestDTO;
import com.example.demo.dto.TokenResponseDTO;
import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private AuthServiceDao authServiceDao;

    public TokenResponseDTO login(LoginRequestDTO request) {
        return authServiceDao.login(request);
    }
}