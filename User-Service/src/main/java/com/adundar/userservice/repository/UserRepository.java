package com.adundar.userservice.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.adundar.userservice.model.User;

public interface UserRepository extends MongoRepository<User, String> {

    User findByName(String name);

}
