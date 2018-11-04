package com.practice.youtube.techdummies.countminsketch;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadLocalRandom;

public class ProducerTask implements Runnable {

    BlockingQueue<Character> blockingQueue;

    public static Character[] alphabets = new Character[26];

    static {
        for (int i = 0; i < 26; i++) {
            alphabets[i] = (char)('A' + i);
        }
        System.out.println("Alphabets array : " + alphabets);
    }

    public ProducerTask(final BlockingQueue<Character> blockingQueue) {
        this.blockingQueue = blockingQueue;
    }

    @Override
    public void run() {

        while (true) {
            final int randomNumber = ThreadLocalRandom.current().nextInt(26);
            blockingQueue.add(alphabets[randomNumber]);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
