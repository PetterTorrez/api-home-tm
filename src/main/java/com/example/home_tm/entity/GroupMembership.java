package com.example.home_tm.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

@Entity
@Table(name="group_membership", uniqueConstraints = {
    @UniqueConstraint(columnNames = { "user_id", "group_id"})
})
public class GroupMembership {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name="user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name="group_id", nullable = false)
    private UserGroup group;

    @ManyToOne
    @JoinColumn(name="role_id", nullable = false)
    private Role role;
}
