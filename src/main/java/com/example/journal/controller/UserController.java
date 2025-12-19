package com.example.journal.controller;

import com.example.journal.entity.UserEntity;
import com.example.journal.service.UserService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@AllArgsConstructor
public class UserController {

    private UserService service;

    @GetMapping // get all users
    public ResponseEntity<List<UserEntity>> getAllUsers() {
        List<UserEntity> result = service.getAllUsers();
        if(result.isEmpty())
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(result, HttpStatus.ACCEPTED);
    }

    @GetMapping("/name/{name}") // get by name
    public ResponseEntity<UserEntity> getByName(@PathVariable String name) {
        UserEntity result = service.getUserByName(name);
        if(result != null)
            return new ResponseEntity<>(result, HttpStatus.ACCEPTED);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping
    public ResponseEntity<UserEntity> updateUser(@RequestBody UserEntity userEntity) {
        UserEntity result = service.updateUser(userEntity);
        if(result == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(result, HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/name/{name}")
    public ResponseEntity<String> deleteUser(@PathVariable String name) {
        return new ResponseEntity<>(service.deleteUser(name), HttpStatus.ACCEPTED);
    }

    @DeleteMapping
    public ResponseEntity<String> deleteAllUsers() {
        return new ResponseEntity<>(service.deleteAllUsers(), HttpStatus.ACCEPTED);
    }
}
