package com.example.home_tm.entity;

import jakarta.persistence.*;

@Entity
@Table(
    name="user_group",
    uniqueConstraints = {
        @UniqueConstraint(columnNames = {"name", "created_by"})
    }
)
public class UserGroup {
    public UserGroup() {}
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(unique = true, nullable = false)
    private String name;

    @ManyToOne(optional = false)
    @JoinColumn(name = "created_by", nullable = false)
    private User createdBy;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public User getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(User createdBy) {
        this.createdBy = createdBy;
    }   
}
