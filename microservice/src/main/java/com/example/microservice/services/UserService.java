package com.example.microservice.services;

import com.example.microservice.dtos.UserDTO;
import com.example.microservice.enums.RoleType;
import org.apache.coyote.BadRequestException;

import java.util.List;

public interface UserService {
    UserDTO createUser(UserDTO user) throws BadRequestException;

    List<UserDTO> getAllUsers();

    UserDTO updateUser(UserDTO userDTO);

    List<RoleType> getAllRoles();
}
