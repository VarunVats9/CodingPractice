package com.practice.concurrency;

import java.util.concurrent.SynchronousQueue;

/**
 * Date : 13 Dec, 2018
 * Time : 3:10 PM
 *
 * @author : Varun Vats (varunvats32@gmail.com)
 */
public class Consumer implements Runnable {

    private SynchronousQueue<Integer> queue;

    public Consumer(final SynchronousQueue<Integer> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        while (true) {
            try {
                System.out.println("Consumer removed the item :" + queue.take());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
