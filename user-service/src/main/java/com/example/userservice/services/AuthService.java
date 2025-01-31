package com.example.userservice.services;

import com.example.userservice.dtos.SignupUser;

public interface AuthService {
    public void signup(SignupUser signupUser);

//    public String login(LoginUser loginRequest);
}
