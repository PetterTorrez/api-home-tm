package com.example.home_tm.repository;

import com.example.home_tm.entity.Role;
import com.example.home_tm.enums.RoleName;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Integer> {
    Optional<Role> findByName(RoleName name);
}
