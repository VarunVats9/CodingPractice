package com.practice.design.pubsub.model;

import java.io.Serializable;
import java.util.UUID;

public class Message<T> implements Serializable {

    private static final long serialVersionUID = 1;
    private T payload;
    private UUID messageId;

    public Message(T payload, UUID messageId) {
        this.payload = payload;
        this.messageId = messageId;
    }

    public T getPayload() {
        return this.payload;
    }
}
