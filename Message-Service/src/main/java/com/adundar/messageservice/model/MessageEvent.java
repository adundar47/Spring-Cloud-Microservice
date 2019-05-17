package com.adundar.messageservice.model;

import java.io.Serializable;

public class MessageEvent implements Serializable {

    private static final long serialVersionUID = -6836843913369688364L;
    private EventType         eventType;
    private Message           message;

    public EventType getEventType() {
        return eventType;
    }

    public void setEventType(EventType eventType) {
        this.eventType = eventType;
    }

    public Message getMessage() {
        return message;
    }

    public void setMessage(Message message) {
        this.message = message;
    }

}
