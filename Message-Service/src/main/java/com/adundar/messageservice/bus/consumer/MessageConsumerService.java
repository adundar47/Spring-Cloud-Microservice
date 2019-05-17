package com.adundar.messageservice.bus.consumer;

import com.adundar.messageservice.model.User;

public interface MessageConsumerService {

    void handleUserCreateEvent(User user);

    void handleUserUpdateEvent(User user);

    void handleUserDeleteEvent(User user);

}
