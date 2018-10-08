package com.practice.youtube.channel.techdummies.autosuggestion;

public class AutoSuggestion {

    private static final int TEN_THOUSAND_REQUESTS = 10000;
    private static final int START = 0;
    private static final String SEPARATOR = "-------------------------------------------------" +
            "--------------------------------------------";

    public static void main(String[] args) {

        // Setup the trie data structure.
        Trie trie = new Trie();
        trie.addWordWithRating("DOG", 9);
        trie.addWordWithRating("DOLL", 11);
        trie.addWordWithRating("DONT", 21);
        trie.addWordWithRating("DART", 1);
        trie.addWordWithRating("DIP", 5);
        trie.addWordWithRating("DOLLAR", 51);
        trie.addWordWithRating("DOGE", 15);
        trie.addWordWithRating("OLD", 3);

        // Query the trie, without doing any pre-compute.
        {
            System.out.println("WITHOUT pre-compute answer : " + trie.wordsWithGivenPrefixWithoutPreCompute("D"));
            final long start = System.currentTimeMillis();
            // Assume we have made the request 10,000 times.
            for (int i = START; i <= TEN_THOUSAND_REQUESTS; i++) {
                trie.wordsWithGivenPrefixWithoutPreCompute("D");
            }
            System.out.println("Time taken to run auto suggestion 10000 times WITHOUT pre-compute : [" + (System.currentTimeMillis() - start) + "] ms.");
        }

        System.out.println(SEPARATOR);

        // Let's pre-compute the auto suggestions beforehand.
        trie.prePopulate();

        {
            System.out.println("WITH pre-compute answer : " + trie.wordsWithGivenPrefixWithPreCompute("D"));
            final long start = System.currentTimeMillis();
            // Assume we have made the request 10,000 times.
            for (int i = START; i <= TEN_THOUSAND_REQUESTS; i++) {
                trie.wordsWithGivenPrefixWithPreCompute("D");
            }
            System.out.println("Time taken to run auto suggestion 10000 times WITH pre-compute : [" + (System.currentTimeMillis() - start) + "] ms.");
        }

    }
}
