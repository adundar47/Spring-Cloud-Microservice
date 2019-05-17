package com.adundar.messageservice.bus.producer;

import com.adundar.messageservice.model.MessageEvent;

public interface MessageProducerService {

    void sendCreateMessageEvent(MessageEvent messageEvent);

    void sendDeleteMessageEvent(MessageEvent messageEvent);

}
