package com.practice.youtube.techdummies.ratelimiting.slidinglogs;

import java.util.Deque;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

public class SlidingLogs {

    public static final ConcurrentHashMap<String, Deque<RequestEntry>> slidingLogCounter = new ConcurrentHashMap<>();

    public final static int API_LIMIT_FOR_EACH_USER = 1; // 1 per 4 second.

    public final static long FOUR_SECONDS = TimeUnit.SECONDS.toSeconds(4);

    public static void main(String[] args) {

        ExecutorService loadBalancer = Executors.newFixedThreadPool(1);

        while (true) {

            final int randomUser = ThreadLocalRandom.current().nextInt(10);
            loadBalancer.submit(new UserApiRequest(slidingLogCounter, randomUser));

            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}
