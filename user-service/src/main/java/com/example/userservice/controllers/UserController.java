package com.example.userservice.controllers;

import com.example.userservice.dtos.UserDTO;
import com.example.userservice.services.UserService;
import jakarta.validation.Valid;
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

//    @PostMapping("/create")
//    public ResponseEntity<?> createUser(@Valid @RequestBody UserDTO userDTO) throws BadRequestException {
//        return ResponseEntity.ok(userService.createUser(userDTO));
//    }

    @GetMapping("/roles")
    public ResponseEntity<?> getAllRoles() {
        return ResponseEntity.ok(userService.getAllRoles());
    }

    @GetMapping("/existUser/{userId}")
    public ResponseEntity<?> existUserById(@PathVariable Long userId) {
        return ResponseEntity.ok(userService.existUser(userId));
    }

    @GetMapping("/{userId}")
    public ResponseEntity<?> getUserById(@PathVariable Long userId) {
        return ResponseEntity.ok(userService.getUserById(userId));
    }
}
