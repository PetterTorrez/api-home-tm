package com.example.home_tm.service;

import java.time.LocalDateTime;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.home_tm.dto.user.UserRequestDTO;
import com.example.home_tm.dto.user.UserResponseDTO;
import com.example.home_tm.dto.user.UserUpdateRequestDTO;
import com.example.home_tm.entity.User;
import com.example.home_tm.mapper.UserMapper;
import com.example.home_tm.repository.UserRepository;

import jakarta.persistence.EntityNotFoundException;
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

    public UserResponseDTO findUserById(Integer id) {
        User user = this.userRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("User not found with ID: " + id));

        if (user.getDeletedAt() != null) {
            throw new EntityNotFoundException("User with ID " + id + " not found.");
        }

        return userMapper.toResponseDTO(user);
    }
    
    @Transactional
    public UserResponseDTO updateUser(Integer id, UserUpdateRequestDTO userDto) {
        User user = this.userRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("User not found with ID: " + id));

        if (user.getDeletedAt() != null) {
            throw new EntityNotFoundException("User with ID " + id + " not found.");
        }

        if (userDto.getName() != null) {
            user.setUsername(userDto.getName());
        }

        if (userDto.getEmail() != null) {
            if (userRepository.findByEmail(userDto.getEmail()).isPresent()) {
                throw new IllegalArgumentException("Email already exists: " + userDto.getEmail());
            }
            user.setEmail(userDto.getEmail());
        }
        
        if (userDto.getPassword() != null) {
            user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        }
 
        this.userRepository.save(user);

        return userMapper.toResponseDTO(user);
    }

    @Transactional
    public void deleteUserById(Integer id) {
        User user = this.userRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("User not found with ID: " + id));

        if (user.getDeletedAt() != null) {
            throw new EntityNotFoundException("User with ID " + id + " not found.");
        }

        user.setDeletedAt(LocalDateTime.now());
        this.userRepository.save(user);
    }
}
