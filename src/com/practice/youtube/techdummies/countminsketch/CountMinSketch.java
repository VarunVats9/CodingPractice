package com.practice.youtube.techdummies.countminsketch;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class CountMinSketch {

    public static void main(String[] args) throws InterruptedException {

        BlockingQueue<Character> blockingQueue = new LinkedBlockingQueue();

        Thread producer = new Thread(new ProducerTask(blockingQueue));
        Thread consumer = new Thread(new ConsumerTask(blockingQueue));

        producer.start();
        consumer.start();

        producer.join();
        consumer.join();
    }

}
