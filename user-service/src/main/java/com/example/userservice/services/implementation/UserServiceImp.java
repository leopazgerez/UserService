package com.example.userservice.services.implementation;

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

    @Override
    public UserDTO createUser(UserDTO user) throws BadRequestException {
        if (userRepository.existsByUserName(user.getUserName())) {
            throw new UserNameAlreadyExistException("Username already registered");
        }
        if (userRepository.existsByEmail(user.getEmail())) {
            throw new EmailAlreadyExistException("Email already registered");
        }
        if (user.getUserName().isEmpty() | user.getEmail().isEmpty()) {
            throw new BadRequestException("Username or email is empty");
        }
        User savedUser = userRepository.save(userMapper.dtoToEntity(user));
        return userMapper.entityToDto(savedUser);
    }

    @Override
    public List<UserDTO> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(userMapper::entityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public UserDTO updateUser(UserDTO userDTO) {
        User foundUser = userRepository.findById(userDTO.getId()).orElseThrow(() -> new UserNotFoundException("User not found"));
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
