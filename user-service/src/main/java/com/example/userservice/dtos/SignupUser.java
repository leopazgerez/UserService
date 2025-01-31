package com.example.userservice.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Email;

public record SignupUser(@JsonProperty("username")
                         @NotBlank(message = "Nombre de usuario es requerido")
                         String username,
                         @JsonProperty("password")
                         @NotBlank(message = "Nombre de usuario es requerido")
                         String password,
                         @JsonProperty("email")
                         @NotBlank(message = "Email es requerido")
                         @Email
                         String email,
                         @JsonProperty("roleTypeId")
                         @NotNull(message = "Role id es requerido")
                         Long roleTypeId) {
}
