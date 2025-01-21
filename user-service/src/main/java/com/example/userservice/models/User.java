package com.example.userservice.models;

import com.example.userservice.enums.RoleType;
import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue
    private Long id;
    @Column()
    private String userName;
    @Column(unique = true)
    private String email;
    @Enumerated(EnumType.STRING)
    private RoleType role;

    public User() {
    }

    public Long getId() {
        return id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public RoleType getRole() {
        return role;
    }

    public void setRole(Long id) {
        this.role = id == 1 ? RoleType.ADMIN : RoleType.USER;
    }
}
