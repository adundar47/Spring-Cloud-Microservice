package com.adundar.messageservice.model;

public enum EventType {
    CREATED("CREATED"), DELETED("DELETED");

    private final String type;

    private EventType(final String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return type;
    }

    public static EventType fromString(String text) {
        if (text != null) {
            for (EventType eventType : EventType.values()) {
                if (text.equals(eventType.type)) {
                    return eventType;
                }
            }
        }
        return null;
    }
}
