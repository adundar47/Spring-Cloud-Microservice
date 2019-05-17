package com.adundar.messageservice.bus.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.adundar.messageservice.cache.service.UserCacheService;
import com.adundar.messageservice.model.User;

@Service
public class KafkaMessageConsumerManager implements MessageConsumerService {

    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    @Autowired
    UserCacheService     userCacheService;

    @KafkaListener(topics = "${kafka.topic.createUser}")
    @Override
    public void handleUserCreateEvent(User user) {
        LOGGER.debug("User Create Message Received -> {}", user.toString());
        LOGGER.debug("[handleUserCreateEvent]: handleUserCreateEvent is processing. User: {}", user);
        userCacheService.save(user);
    }

    @KafkaListener(topics = "${kafka.topic.updateUser}")
    @Override
    public void handleUserUpdateEvent(User user) {
        LOGGER.debug("User Update Message Received -> {}", user.toString());
        LOGGER.debug("[handleUserUpdateEvent]: handleUserUpdateEvent is processing. User: {}", user);
        userCacheService.update(user);
    }

    @KafkaListener(topics = "${kafka.topic.deleteUser}")
    @Override
    public void handleUserDeleteEvent(User user) {
        LOGGER.debug("User Delete Message Received -> {}", user.toString());
        LOGGER.debug("[handleUserDeleteEvent]: handleUserDeleteEvent is processing. User: {}", user);
        userCacheService.delete(user.getName());
    }

}
