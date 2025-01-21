package com.example.microservice.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {

    @GetMapping("/all")
    public ResponseEntity<?> getAllUsers() {
        return ResponseEntity.ok("hola");
    }

    @PostMapping("/create")
    public ResponseEntity<?> createUser() {
        return ResponseEntity.ok("Creado");
    }

    @GetMapping("/roles")
    public ResponseEntity<?> getAllRoles() {
        return ResponseEntity.ok("Roles");
    }
}
