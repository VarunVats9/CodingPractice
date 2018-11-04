package com.practice.youtube.techdummies.ratelimiting.tokenbucket;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadLocalRandom;

public class TokenBucket {

    public static final ConcurrentHashMap<String, TokenEntry> userTokenCount = new ConcurrentHashMap<>();

    public final static int API_LIMIT_FOR_EACH_USER = 5;

    public static void main(String[] args) {

        ExecutorService loadBalancer = Executors.newFixedThreadPool(10);

        while (true) {

            final int randomUser = ThreadLocalRandom.current().nextInt(10);
            loadBalancer.submit(new UserApiRequest(userTokenCount, randomUser));

            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
