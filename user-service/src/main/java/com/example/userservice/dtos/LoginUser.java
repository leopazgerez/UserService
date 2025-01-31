package com.example.userservice.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;

public record LoginUser(@JsonProperty("email") String email, @JsonProperty("password") String password) {
}
