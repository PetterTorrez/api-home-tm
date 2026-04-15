package com.example.home_tm.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.home_tm.entity.User;
import com.example.home_tm.repository.UserRepository;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public ResponseEntity<User> findUserById(Integer id) {
        return this.userRepository.findById(id)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }
    
    public ResponseEntity<Iterable<User>> findAllUsers() {
        Iterable<User> users = this.userRepository.findAll();
        return ResponseEntity.ok(users);
    }
}
