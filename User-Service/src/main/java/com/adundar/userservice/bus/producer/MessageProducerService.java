package com.adundar.userservice.bus.producer;

import com.adundar.userservice.model.User;

public interface MessageProducerService {

    void sendCreateUserEvent(User user);

    void sendUpdateUserEvent(User user);

    void sendDeleteUserEvent(User user);

}
