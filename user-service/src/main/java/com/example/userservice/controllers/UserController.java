package com.example.userservice.controllers;

import com.example.userservice.dtos.UserDTO;
import com.example.userservice.services.UserService;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping("/all")
    public ResponseEntity<?> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @PostMapping("/create")
    public ResponseEntity<?> createUser(@RequestBody UserDTO userDTO) throws BadRequestException {
        return ResponseEntity.ok(userService.createUser(userDTO));
    }

    @GetMapping("/roles")
    public ResponseEntity<?> getAllRoles() {
        return ResponseEntity.ok(userService.getAllRoles());
    }

    @GetMapping("/userId/{userId}")
    public ResponseEntity<?> getAllRoles(@PathVariable Long userId) {
        return ResponseEntity.ok(userService.existUser(userId));
    }
}
