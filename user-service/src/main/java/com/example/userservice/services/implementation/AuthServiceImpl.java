package com.example.userservice.services.implementation;

import com.example.userservice.Utils.RabbitValues;
import com.example.userservice.dtos.LoginUser;
import com.example.userservice.dtos.SignupUser;
import com.example.userservice.exceptions.EmailAlreadyExistException;
import com.example.userservice.exceptions.UserNameAlreadyExistException;
import com.example.userservice.mappers.UserMapper;
import com.example.userservice.models.User;
import com.example.userservice.repositories.UserRepository;
import com.example.userservice.services.AuthService;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    RabbitValues rabbitValues;
    @Autowired
    RabbitTemplate rabbitTemplate;

    @Override
    public void signup(SignupUser signupUser) {
        if (userRepository.existsByEmail(signupUser.email())) {
            throw new EmailAlreadyExistException("Email already exists");
        }
        if (userRepository.existsByUserName(signupUser.username())) {
            throw new UserNameAlreadyExistException("Username already exists");
        }
        String encryptPass = passwordEncoder.encode(signupUser.password());
        User savedUser = userRepository.save(userMapper.dtoToEntity(signupUser, encryptPass));
        rabbitTemplate.convertAndSend(rabbitValues.getExchange(), rabbitValues.getRegisteredUserRoutingKey(), userMapper.entityToDto(savedUser));
    }
}
