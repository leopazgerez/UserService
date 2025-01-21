package com.example.userservice.mappers;
import com.example.userservice.dtos.UserDTO;
import com.example.userservice.models.User;

public class UserMapper {
    UserMapper() {
    }

    public User dtoToEntity(UserDTO userDTO) {
        User userEntity = new User();
        userEntity.setUserName(userEntity.getUserName());
        userEntity.setEmail(userEntity.getEmail());
        userEntity.setRole(userDTO.getRoleId());
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
