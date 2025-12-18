package com.example.journal.service;

import com.example.journal.entity.UserEntity;
import com.example.journal.repository.UserRepo;
import lombok.AllArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Component
@AllArgsConstructor
public class UserService {
    private UserRepo userRepo;

    public UserEntity createUser(UserEntity userEntity) {
        userEntity.setCreatedTime(LocalDateTime.now());
        return userRepo.save(userEntity);
    }

    public List<UserEntity> getAllUsers() {
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
}
