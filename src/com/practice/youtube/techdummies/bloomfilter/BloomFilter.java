package com.practice.youtube.techdummies.bloomfilter;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Stream;

public class BloomFilter {

    final static int NUMBER_OF_BITS = 6400;

    final static HashFunction h1 = new HashFunction(11, 9);
    final static HashFunction h2 = new HashFunction(17, 15);
    final static HashFunction h3 = new HashFunction(31, 29);
    final static HashFunction h4 = new HashFunction(61, 59);

    public static void main(String[] args) throws ClassNotFoundException {

        // Bit array of size 64,000 bits (800 bytes)
        BitSet bitSet = new BitSet(NUMBER_OF_BITS);

        // Read the words into an array (4000 words)
        List<String> dictionary = new ArrayList<>();
        URL fileName = BloomFilter.class.getResource("dictionary.txt");

        //read file into stream, try-with-resources
        try (Stream<String> stream = Files.lines(Paths.get(fileName.getFile()))) {
            stream.forEach(word -> dictionary.add(word));
        } catch (IOException e) {
            e.printStackTrace();
        }

        List<String> randomWordList = new ArrayList<>();

        // Prepare the bloom filter with random words from wordList, and add in randomWordList.
        for (int i = 0; i < 1000; i++) {

            final int randomNumber = ThreadLocalRandom.current().nextInt(dictionary.size() - 1);
            final String randomWord = dictionary.get(randomNumber);

            // Populate bloom filter
            bitSet.set(h1.getHashValue(randomWord));
            bitSet.set(h2.getHashValue(randomWord));
            bitSet.set(h3.getHashValue(randomWord));
            bitSet.set(h4.getHashValue(randomWord));

            randomWordList.add(randomWord);
        }


        for (int i = 0; i < 4000; i++) {
            final String word = dictionary.get(i);
            final boolean answerFromBloomFilter = (bitSet.get(h1.getHashValue(word)) && bitSet.get(h2.getHashValue(word))
                    && bitSet.get(h3.getHashValue(word)) && bitSet.get(h4.getHashValue(word)));

            System.out.println("-------------------------------------------------------------------------------------------------------- ");
            System.out.println("Check if the Word [" + word  + "] is present in Bloom Filter");
            System.out.println("ANSWER from bloom filter : [" + answerFromBloomFilter + "]");

            if (answerFromBloomFilter) {
                System.out.println("Actual Answer with less (< 100%) Probability : [" + randomWordList.contains(word) + "]");
            } else {
                System.out.println("Actual Answer will be FALSE with 100 % Probability : [" + randomWordList.contains(word) + "]");
            }

            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    private static class HashFunction {

        private long prime;
        private long odd;

        public HashFunction(final long prime, final long odd) {
            this.prime = prime;
            this.odd = odd;
        }

        public int getHashValue(final String word) {
            int hash = word.hashCode();
            if (hash < 0) {
                hash = Math.abs(hash);
            }
            return calculateHash(hash, prime, odd);
        }

        private int calculateHash(final int hash, final long prime, final long odd) {
            return (int)((((hash % NUMBER_OF_BITS) * prime) % NUMBER_OF_BITS) * odd) % NUMBER_OF_BITS;
        }

    }

}
