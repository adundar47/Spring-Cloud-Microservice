package com.adundar.messageservice.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.adundar.messageservice.model.Message;

public interface MessageRepository extends MongoRepository<Message, String> {

    List<Message> findByUserName(String userName);

}
