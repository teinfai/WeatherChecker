package com.example.demo.dao.impl;

import com.example.demo.dao.AuthServiceDao;
import com.example.demo.dto.LoginRequestDTO;
import com.example.demo.dto.TokenResponseDTO;
import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

@Repository // <---- IMPORTANT!
public class AuthServiceDaoImpl implements AuthServiceDao {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private PasswordEncoder passwordEncoder;


    public TokenResponseDTO login(LoginRequestDTO request) {
        User user = userRepository.findByName(request.getName())
                .orElseThrow(() -> new RuntimeException("User not found: " + request.getName()));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid password for user: " + request.getName());
        }

        String token = jwtUtil.generateToken(user.getName());
        return new TokenResponseDTO(token);
    }
}
