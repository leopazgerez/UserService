package com.example.userservice.dtos;

import com.example.userservice.enums.RoleType;

public class UserDTO {
    private Long id;
    private String userName;
    private String email;
    private Long roleId;

    public UserDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public void setRoleId(RoleType role) {
        this.roleId = role == RoleType.ADMIN ? 1L : 2L;
    }
}
