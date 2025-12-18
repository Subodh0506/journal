package com.example.journal.repository;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;


import com.example.journal.entity.JournalEntity;


public interface JournalRepo extends MongoRepository<JournalEntity, ObjectId> {

}
