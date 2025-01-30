package com.example.userservice.services.implementation;

import com.example.userservice.Utils.RabbitValues;
import com.example.userservice.dtos.UserDTO;
import com.example.userservice.enums.RoleType;
import com.example.userservice.exceptions.EmailAlreadyExistException;
import com.example.userservice.exceptions.UserNameAlreadyExistException;
import com.example.userservice.exceptions.UserNotFoundException;
import com.example.userservice.mappers.UserMapper;
import com.example.userservice.models.User;
import com.example.userservice.repositories.UserRepository;
import com.example.userservice.services.UserService;
import org.apache.coyote.BadRequestException;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImp implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    UserMapper userMapper;
    @Autowired
    RabbitValues rabbitValues;
    @Autowired
    RabbitTemplate rabbitTemplate;

    @Override
    public UserDTO createUser(UserDTO user) throws BadRequestException {
        if (userRepository.existsByUserName(user.getUserName())) {
            throw new UserNameAlreadyExistException("Username already registered");
        }
        if (userRepository.existsByEmail(user.getEmail())) {
            throw new EmailAlreadyExistException("Email already registered");
        }
        User savedUser = userRepository.save(userMapper.dtoToEntity(user));
        rabbitTemplate.convertAndSend(rabbitValues.getExchange(),rabbitValues.getRegisteredUserRoutingKey(),userMapper.entityToDto(savedUser));
        return userMapper.entityToDto(savedUser);
    }

    @Override
    public boolean existUser(Long userId) {
        User userFound = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("User not found"));
        return userFound != null;
    }

    @Override
    public UserDTO getUserById(Long userId) {
        User userFound = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("User not found"));
        return userMapper.entityToDto(userFound);
    }

    @Override
    public List<UserDTO> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(userMapper::entityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public UserDTO updateUser(UserDTO userDTO, Long id) {
        User foundUser = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("User not found"));
        foundUser.setEmail(userDTO.getEmail());
        foundUser.setUserName(userDTO.getUserName());
        User savedUser = userRepository.save(foundUser);
        return userMapper.entityToDto(savedUser);
    }

    @Override
    public List<RoleType> getAllRoles() {
        return List.of(RoleType.values());
    }
}
