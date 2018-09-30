package com.practice.design.objectpool;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class Main {

    public static void main(String[] args) {
        final ObjectPoolService<Integer> service = new ObjectPoolServiceImpl(4, 9);

        List<Thread> listOfThreads = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            final Thread thread = new Thread(new ObjectPoolTask(service), "thread - " + i);
            listOfThreads.add(thread);
        }

        listOfThreads.forEach(Thread::start);

    }

    private static class ObjectPoolTask implements Runnable {

        private final ObjectPoolService<Integer> service;

        public ObjectPoolTask(final ObjectPoolService<Integer> service) {
            this.service = service;
        }

        @Override
        public void run() {
            while (true) {
                final Integer integer = service.tryBorrowObject(5000);
                try {
                    Thread.sleep(ThreadLocalRandom.current().nextInt(1, 10 + 1) * 2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                service.releaseObject(integer);
            }

            /*while (true) {
                final Integer integer = service.borrowObject();
                try {
                    Thread.sleep(ThreadLocalRandom.current().nextInt(1, 10 + 1) * 2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                service.releaseObject(integer);
            }*/
        }
    }
}



