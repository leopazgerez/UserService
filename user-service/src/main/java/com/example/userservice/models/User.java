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
    private String userName, password;
    @Column(unique = true)
    private String email;
    @Enumerated(EnumType.ORDINAL)
    private RoleType role;
    private boolean isEmailValidate;

    public User() {
        this.isEmailValidate = false;
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

    public void setRole(RoleType role) {
        this.role = role;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isEmailValidate() {
        return isEmailValidate;
    }

    public void setEmailValidate(boolean emailValidate) {
        isEmailValidate = emailValidate;
    }
}
