package com.example.demo.controller;

import com.example.demo.dto.UserDetailDto;
import com.example.demo.service.UserDetailService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/user-details")
@Api(tags = "User‑Detail API")
public class UserDetailController {

    private final UserDetailService service;

    public UserDetailController(UserDetailService service) {
        this.service = service;
    }

    @GetMapping
    @ApiOperation("Get all user details (DTO)")
    public List<UserDetailDto> getAll() {
        return service.getAll();
    }
}
