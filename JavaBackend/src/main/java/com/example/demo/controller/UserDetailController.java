package com.example.demo.controller;

import com.example.demo.entity.UserDetail;
import com.example.demo.repository.UserDetailRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserDetailController {

    private final UserDetailRepository userDetailRepository;

    public UserDetailController(UserDetailRepository userDetailRepository) {
        this.userDetailRepository = userDetailRepository;
    }

//    @GetMapping("/user-details")
//    public List<UserDetail> getAllUserDetails() {
//        return userDetailRepository.findAllWithUser();
//    }

}
