package com.example.userservice.services;

import com.example.userservice.dtos.UserDTO;
import com.example.userservice.enums.RoleType;
import org.apache.coyote.BadRequestException;

import java.util.List;

public interface UserService {
    UserDTO createUser(UserDTO user) throws BadRequestException;

    boolean existUser(Long userId);

    List<UserDTO> getAllUsers();

    UserDTO updateUser(UserDTO userDTO, Long id);

    List<RoleType> getAllRoles();
}
