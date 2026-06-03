package com.example.home_tm.repository;

import com.example.home_tm.entity.User;
import com.example.home_tm.entity.UserGroup;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserGroupRepository extends JpaRepository<UserGroup, Long> {
    Optional<UserGroup> findByNameAndCreatedBy(String name, User user);
}
