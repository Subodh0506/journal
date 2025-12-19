package com.example.journal.controller;

import com.example.journal.entity.UserEntity;
import com.example.journal.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/public")
@AllArgsConstructor
public class PublicController {

    private UserService service;

    @GetMapping("/health") // health check endpoint
    public ResponseEntity<String> healthCheck() {
        return ResponseEntity.ok("Health check successful");
    }


    @PostMapping("/add-user") // create user
    public ResponseEntity<UserEntity> createUser(@RequestBody UserEntity userEntity) {
        return new ResponseEntity<>(service.createUser(userEntity), HttpStatus.CREATED);
    }
}
