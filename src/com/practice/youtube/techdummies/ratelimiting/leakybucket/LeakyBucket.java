package com.practice.youtube.techdummies.ratelimiting.leakybucket;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class LeakyBucket {

    public static final int API_REQUEST_LIMIT_OF_THE_SERVER = 5;

    public static final BlockingQueue<Runnable> blockingLifo = new ArrayBlockingQueue<>(API_REQUEST_LIMIT_OF_THE_SERVER);

    public static final RejectedExecutionHandler handler = new RejectedExecutionHandler() {
        @Override
        public void rejectedExecution(final Runnable r, final ThreadPoolExecutor executor) {
            System.out.println("API access BLOCKED");
        }
    };

    public static void main(String[] args) {

        ThreadPoolExecutor loadBalancer = new ThreadPoolExecutor(
                5, 5, 0L, TimeUnit.SECONDS, blockingLifo, handler);

        while (true) {

            final int randomUser = ThreadLocalRandom.current().nextInt(10);
            loadBalancer.submit(new UserApiRequest(randomUser));

            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
