package com.example.userservice.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;

public record SignupUser(@JsonProperty("username")
                         String username,
                         @JsonProperty("password")
                         String password,
                         @JsonProperty("email")
                         String email,
                         @JsonProperty("roleTypeId")
                         Long roleTypeId) {
}
