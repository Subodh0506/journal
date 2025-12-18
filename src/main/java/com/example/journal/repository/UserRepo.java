package com.example.journal.repository;

import com.example.journal.entity.UserEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepo extends MongoRepository<UserEntity, String> {
    UserEntity findByUserName(String username);
}
