package com.example.userservice.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Email;

public record LoginUser(@JsonProperty("email")   @NotBlank(message = "Email es requerido")
                        @Email String email, @JsonProperty("password") @NotBlank(message = "Constraseña es requerida") String password) {
}
