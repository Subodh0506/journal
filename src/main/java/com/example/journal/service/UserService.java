package com.example.journal.service;

import com.example.journal.entity.UserEntity;
import com.example.journal.repository.UserRepo;
import lombok.AllArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.server.authorization.AuthorizationContext;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class UserService {

    private UserRepo userRepo;

    private static final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public UserEntity createUser(UserEntity userEntity) {
        userEntity.setCreatedTime(LocalDateTime.now());
        userEntity.setPassword(passwordEncoder.encode(userEntity.getPassword()));
        return userRepo.save(userEntity);
    }

    public List<UserEntity> getAllUsers() {
//        AuthorizationContext auth =
        return userRepo.findAll();
    }

    public UserEntity getUserByName(String userName) {
        return userRepo.findByUserName(userName);
    }

    @Transactional
    public UserEntity updateUser(UserEntity userEntity) {
        if(userEntity.getId() == null)
            return null;
        UserEntity old = userRepo.findById(userEntity.getId()).orElse(null);
        if(old != null) {
            old.setUserName(userEntity.getUserName());
            old.setPassword(userEntity.getPassword());
            return userRepo.save(old);
        }
        return null;
    }

    @Transactional
    public String deleteUser(String userName) {
        UserEntity checks = userRepo.findByUserName(userName);
        if(checks != null)
        {
            userRepo.deleteById(checks.getId());
            return "user deleted";
        }
        return "user not found";
    }

    public String deleteAllUsers() {
        userRepo.deleteAll();
        return "deleted all users";
    }
}
