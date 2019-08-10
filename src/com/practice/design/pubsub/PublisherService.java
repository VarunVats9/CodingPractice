package com.practice.design.pubsub;

import com.practice.design.pubsub.model.Message;

public class PublisherService {

    private BrokerService brokerService;

    public PublisherService(BrokerService brokerService) {
        this.brokerService = brokerService;
    }

    public void generateMessage() {
        /*Message<String> message = new StringBuffer("Message");
        this.brokerService.publishMessage(topic, message);*/
    }
}
