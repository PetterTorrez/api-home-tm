package com.example.home_tm.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.home_tm.entity.User;

public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByEmail(String email);
}
