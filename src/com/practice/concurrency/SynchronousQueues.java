package com.practice.concurrency;

import java.util.concurrent.SynchronousQueue;

/**
 * Date : 13 Dec, 2018
 * Time : 3:07 PM
 *
 * @author : Varun Vats (varunvats32@gmail.com)
 */
public class SynchronousQueues {

    public static void main(String[] args) throws InterruptedException {
        SynchronousQueue<Integer> queue = new SynchronousQueue<>();
        Thread t = new Thread(new Consumer(queue));
        t.start();
        for (int i = 0; i < 100; i++) {
            if (queue.size() == 0) {
                queue.put(i);
            }
            Thread.sleep(5000);
        }
    }
}
