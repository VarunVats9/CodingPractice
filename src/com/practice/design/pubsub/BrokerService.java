package com.practice.design.pubsub;

import com.practice.design.pubsub.model.Message;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Objects;

public class BrokerService {

    private Map<String, LinkedList<Message>> map;
    private WorkerService workerService;

    public BrokerService(WorkerService workerService) {
        map = new HashMap<>();
        this.workerService = workerService;
    }

    public boolean publishMessage(String topic, Message message) {
        LinkedList<Message> queue = map.getOrDefault(topic, null);
        if (Objects.isNull(queue)) {
            queue = new LinkedList<>();
        }
        queue.add(message);
        map.put(topic, queue);
        return true;
    }

    public void deleteMessage(String topic, Message message) {
        // think of some efficient DS.
        LinkedList<Message> queue = map.getOrDefault(topic, null);
        queue.remove(message);
    }

}
