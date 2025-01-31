package com.example.userservice.mappers;

import com.example.userservice.dtos.SignupUser;
import com.example.userservice.dtos.UserDTO;
import com.example.userservice.enums.RoleType;
import com.example.userservice.models.User;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class UserMapper {
    UserMapper() {
    }

    public User dtoToEntity(UserDTO userDTO) {
        User userEntity = new User();
        userEntity.setUserName(userDTO.getUserName());
        userEntity.setEmail(userDTO.getEmail());
        userEntity.setRole(userDTO.getRole());
        return userEntity;
    }

    public UserDTO entityToDto(User userEntity) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(userEntity.getId());
        userDTO.setEmail(userEntity.getEmail());
        userDTO.setUserName(userEntity.getUserName());
        userDTO.setRoleId(userEntity.getRole());
        return userDTO;
    }

    public User dtoToEntity(SignupUser signupUser, String encodedPass) {
        RoleType userRole = RoleType.fromOrdinal(signupUser.roleTypeId());
        User user = new User();
        user.setEmail(signupUser.email());
        user.setPassword(encodedPass);
        user.setUserName(signupUser.username());
        user.setRole(userRole);
        return user;
    }
}
