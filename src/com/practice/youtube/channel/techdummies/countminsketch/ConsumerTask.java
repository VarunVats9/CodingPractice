package com.practice.youtube.channel.techdummies.countminsketch;

import java.util.concurrent.BlockingQueue;

public class ConsumerTask implements Runnable {

    private static final int H1 = 0;
    private static final int H2 = 1;
    private static final int H3 = 2;
    private static final int H4 = 3;

    final static int LIMIT = 100;
    final int[][] sketch = new int[4][LIMIT];

    final HashFunction h1 = new HashFunction(11, 9);
    final HashFunction h2 = new HashFunction(17, 15);
    final HashFunction h3 = new HashFunction(31, 29);
    final HashFunction h4 = new HashFunction(61, 59);

    BlockingQueue<Character> blockingQueue;
    final int[] actualFrequency = new int[26];

    public ConsumerTask(final BlockingQueue<Character> blockingQueue) {
        this.blockingQueue = blockingQueue;
    }

    @Override
    public void run() {

        while (true) {
            Character alphabet = 'A';
            try {
                alphabet = blockingQueue.take();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            actualFrequency[alphabet - 'A']++;

            sketch[H1][h1.getHashValue(alphabet)]++;
            sketch[H2][h1.getHashValue(alphabet)]++;
            sketch[H3][h1.getHashValue(alphabet)]++;
            sketch[H4][h1.getHashValue(alphabet)]++;

            System.out.println("--------------------------------------------------------------------------");

            System.out.println("Actual Frequency of " + alphabet + " : " + actualFrequency[alphabet - 'A']);
            System.out.println("Count Min Sketch of " + alphabet + " : " + findMinimum(sketch[H1][h1.getHashValue(alphabet)],
                    sketch[H2][h1.getHashValue(alphabet)], sketch[H3][h1.getHashValue(alphabet)], sketch[H4][h1.getHashValue(alphabet)]));
        }
    }

    private int findMinimum(final int a, final int b, final int c, final int d) {
        return Math.min( Math.min(a, b), Math.min(c, d) );
    }

    private static class HashFunction {

        private int prime;
        private int odd;

        public HashFunction(final int prime, final int odd) {
            this.prime = prime;
            this.odd = odd;
        }

        public int getHashValue(final Character character) {
            return ((character.hashCode() * this.prime) ^ this.odd) % LIMIT;
        }

    }
}
