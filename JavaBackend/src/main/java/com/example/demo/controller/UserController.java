package com.example.demo.controller;

import com.example.demo.entity.User;
import com.example.demo.service.MessageSender;
import com.example.demo.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@Api(value = "User API", tags = "User API")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private MessageSender messageSender;


    @GetMapping("retrieveAllUser")
    @ApiOperation(value = "Get all users")
    public List<User> getAllUsers() {

        return userService.getAllUsers();
    }

    @GetMapping("retrieveUserDetail/{id}")
    @ApiOperation(value = "Get users with detail")
    public List<User> retrieveUserWithDetail(@PathVariable Long id) {
        return userService.getAllUsersWithDetail(id);
    }

    @PostMapping("/sendToQueue")
    @ApiOperation(value = "Test sending user to RabbitMQ + service")
    public String sendToQueue(@RequestBody User user) {
        messageSender.send(user);
        return "✅ Sent to queue";
    }

    @GetMapping("retrieveSingleUser/{id}")
    @ApiOperation(value = "Get user by ID")
    public User getUserById(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    @PostMapping("/register")
    @ApiOperation(value = "Create a new user")
    public User createUser(@RequestBody User user) {
        return userService.createUser(user);
    }

    @PutMapping("update/{id}")
    @ApiOperation(value = "Update user")
    public User updateUser(@PathVariable Long id, @RequestBody User user) {
        return userService.updateUser(id, user);
    }

    @DeleteMapping("delete/{id}")
    @ApiOperation(value = "Delete user")
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
    }
}