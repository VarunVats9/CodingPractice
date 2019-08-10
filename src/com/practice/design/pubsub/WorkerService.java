package com.practice.design.pubsub;

import com.practice.design.pubsub.model.Message;
import com.practice.design.pubsub.model.SubscriberQueue;

import java.util.List;

public class WorkerService {

    private SubscriberService subscriberService;
    private BrokerService brokerService;

    public WorkerService(SubscriberService subscriberService, BrokerService brokerService) {
        this.subscriberService = subscriberService;
        this.brokerService = brokerService;
    }

    public void fanOut(String topic, Message message) {
        List<SubscriberQueue> queues = this.subscriberService.getSubscriberQueues();
        queues.forEach(q -> {
            q.pushToQueue(message);
        });
        this.brokerService.deleteMessage(topic, message);
    }
}
