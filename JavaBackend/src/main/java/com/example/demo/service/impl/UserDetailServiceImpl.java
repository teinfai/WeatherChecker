package com.example.demo.service.impl;

import com.example.demo.dto.UserDetailDto;
import com.example.demo.mapper.UserDetailMapper;
import com.example.demo.repository.UserDetailRepository;
import com.example.demo.service.UserDetailService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class UserDetailServiceImpl implements UserDetailService {

    private final UserDetailRepository repo;
    private final UserDetailMapper     mapper;

    public UserDetailServiceImpl(UserDetailRepository repo, UserDetailMapper mapper) {
        this.repo   = repo;
        this.mapper = mapper;
    }

    @Override
    public List<UserDetailDto> getAll() {
        return mapper.toDtoList(repo.findAllWithUser());
    }
}
