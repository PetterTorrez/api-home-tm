package com.example.home_tm.service;

import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.home_tm.dto.user.UserRequestDTO;
import com.example.home_tm.dto.user.UserResponseDTO;
import com.example.home_tm.entity.User;
import com.example.home_tm.mapper.UserMapper;
import com.example.home_tm.repository.UserRepository;

import jakarta.transaction.Transactional;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public UserService(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    @Transactional
    public UserResponseDTO createUser(UserRequestDTO userDto) {
        if (userRepository.findByEmail(userDto.getEmail()).isPresent()) {
            throw new IllegalArgumentException("Email already exists: " + userDto.getEmail());
        }
        User user = userMapper.toEntity(userDto);

        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        User userSaved = userRepository.save(user);

        return userMapper.toResponseDTO(userSaved);
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
