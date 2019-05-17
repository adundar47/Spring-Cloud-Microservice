package com.adundar.messageservice.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.adundar.messageservice.cache.service.UserCacheService;
import com.adundar.messageservice.exception.NotFoundException;
import com.adundar.messageservice.model.Message;
import com.adundar.messageservice.model.Result;
import com.adundar.messageservice.model.User;
import com.adundar.messageservice.repository.MessageRepository;
import com.adundar.messageservice.utils.Utils;

@Service
public class MessageService {

    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    @Autowired
    MessageRepository    messageRepository;

    @Autowired
    UserCacheService     userCacheService;

    public Message createMessage(Message message) throws NotFoundException {

        LOGGER.debug("[createMessage]: Message is : {}", message.toString());

        User user = userCacheService.get(message.getUserName());

        if (user == null)
            throw new NotFoundException(Utils.getUserNameNotFoundError(message.getUserName()));

        return messageRepository.save(message);
    }

    public Result<?> getAllMessages() {
        LOGGER.debug("[getAllMessages]: Get All Messages request is processing.");
        return Result.success(HttpStatus.OK, messageRepository.findAll());
    }

    public Result<?> retrieveMessage(String messageId) throws NotFoundException {
        LOGGER.debug("[retrieveMessage]: Get Message request is processing. messageId -> {}", messageId);

        Message message = messageRepository.findOne(messageId);
        if (message == null)
            throw new NotFoundException(Utils.getMessageIdNotFoundError(messageId));

        LOGGER.debug("[retrieveMessage]: Get Message request is processing. message -> {}", message.toString());

        return Result.success(HttpStatus.OK, message);
    }

    public Result<?> retrieveUserMessages(String userName) {
        LOGGER.debug("[retrieveUserMessages]: Get Message request is processing. userName -> {}", userName);

        List<Message> messages = messageRepository.findByUserName(userName);

        return Result.success(HttpStatus.OK, messages);
    }

    public Result<?> deleteMessage(String messageId) throws NotFoundException {
        LOGGER.debug("[deleteMessage]: Delete Message request is processing. messageId -> {}", messageId);

        Message message = messageRepository.findOne(messageId);
        if (message == null)
            throw new NotFoundException(Utils.getMessageIdNotFoundError(messageId));

        messageRepository.delete(message);

        LOGGER.debug("[deleteMessage]: Delete Message request is processing. message -> {}", message.toString());

        return Result.success(HttpStatus.OK, message);
    }

}
