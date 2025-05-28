package com.example.demo.service;

import com.example.demo.entity.User;
import com.example.demo.entity.UserDetail;
import com.example.demo.exception.UserNotFoundException;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Transactional
    public List<User> getAllUsersWithDetail(Long id) {
        List<Long> ids = new ArrayList<>();
        ids.add(id);
        List<User> users = userRepository.findAllById(ids);

        for (User user : users) {
            UserDetail detail = user.getUserDetail(); // triggers lazy loading
            if (detail != null) {
                detail.getHobby(); // force initialize lazy data
            }
        }

        return users;
    }

    public User getUserById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
    }

    @Transactional
    public User createUser(User user) {

        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            System.out.println("❌ Email already exists: " + user.getEmail());
            return null;
        }

        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        return userRepository.save(user);
    }

    public User updateUser(Long id, User userDetails) {
        User user = getUserById(id);
        user.setName(userDetails.getName());
        user.setEmail(userDetails.getEmail());
        return userRepository.save(user);
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}
