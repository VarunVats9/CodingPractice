package com.practice.design.pubsub;

import com.practice.design.pubsub.model.SubscriberQueue;

import java.util.ArrayList;
import java.util.List;

public class SubscriberService {

    private List<SubscriberQueue> subscriberQueues;

    public SubscriberService() {
        this.subscriberQueues = new ArrayList<>();
    }

    public List<SubscriberQueue> getSubscriberQueues() {
        return this.subscriberQueues;
    }

}
