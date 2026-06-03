package com.example.home_tm.repository;

import com.example.home_tm.entity.GroupMembership;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GroupMembershipRepository
    extends JpaRepository<GroupMembership, Integer> {}
