package com.example.demo.dao;

import com.example.demo.dto.LoginRequestDTO;
import com.example.demo.dto.TokenResponseDTO;

public interface AuthServiceDao {

    TokenResponseDTO login(LoginRequestDTO request);

}
