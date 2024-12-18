package com.codewarrior.Journal_App.repository;

import com.codewarrior.Journal_App.entity.User;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, ObjectId> {
}
