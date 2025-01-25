package com.example.userservice.dtos;

import com.example.userservice.enums.RoleType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Email;

public class UserDTO {
    private Long id;
    @NotBlank(message = "El usuario es requerido")
    private String userName;
    @NotBlank(message = "Email es requerido")
    @Email
    private String email;
    @NotNull(message = "Role id es requerido")
    private RoleType role;

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

    public RoleType getRole() {
        return role;
    }

    public void setRoleId(RoleType role) {
        this.role = role;
    }
}
