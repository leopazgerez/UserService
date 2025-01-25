package com.example.userservice.mappers;

import com.example.userservice.dtos.UserDTO;
import com.example.userservice.models.User;
import org.springframework.stereotype.Component;

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
}
