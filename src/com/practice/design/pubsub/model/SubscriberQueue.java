package com.practice.design.pubsub.model;

import com.practice.design.pubsub.SubscriberService;

import java.util.LinkedList;

public class SubscriberQueue {

    private LinkedList<Message> queue;

    public SubscriberQueue() {
        this.queue = new LinkedList<>();
    }

    public boolean pushToQueue(Message message) {
        this.queue.add(message);
        return true;
    }

}
