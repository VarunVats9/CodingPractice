package com.practice.youtube.techdummies.autosuggestion;

public class AutoSuggestion {

    private static final int TEN_THOUSAND_REQUESTS = 10000;
    private static final int START = 0;
    private static final String SEPARATOR = "--------------------------------------------------------------------" +
            "---------------------------------------------------------------";

    public static void main(String[] args) {

        /*
         * Two ways to do pre-compute.
         * 1. One while you are adding the word and rating. (Good approach)
         * 2. Trie already been made, then you run the pre-compute. (Bad approach)
         */


        // BAD APPROACH.
        {
            System.out.println("************** BAD APPROACH : RUN PRE-COMPUTE ONCE TRIE HAS BEEN FORMED **************\n");

            final long badApproachStart = System.currentTimeMillis();

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
                System.out.println("Time taken to run auto suggestion 10000 times WITHOUT pre-compute : [" + (System.currentTimeMillis() - start) + "] ms.\n");
            }

            // Let's pre-compute the auto suggestions beforehand.
            trie.prePopulate();

            // Query the trie, after pre-compute is done.
            {
                System.out.println("WITH pre-compute answer : " + trie.wordsWithGivenPrefixWithPreCompute("D"));
                final long start = System.currentTimeMillis();
                // Assume we have made the request 10,000 times.
                for (int i = START; i <= TEN_THOUSAND_REQUESTS; i++) {
                    trie.wordsWithGivenPrefixWithPreCompute("D");
                }
                System.out.println("Time taken to run auto suggestion 10000 times WITH pre-compute : [" + (System.currentTimeMillis() - start) + "] ms.\n");
            }

            System.out.println("Time taken to run pre-compute BAD APPROACH : [" + (System.currentTimeMillis() - badApproachStart) + "] ms.\n");
        }


        System.out.println(SEPARATOR);

        // GOOD APPROACH.
        {
            System.out.println("\n************** GOOD APPROACH : RUN PRE-COMPUTE WHILE TRIE IS BEING FORMED **************\n");

            final long goodApproachStart = System.currentTimeMillis();

            // Setup the trie data structure.
            Trie trie = new Trie();
            trie.addWordWithRatingAndDoPreCompute("DOG", 9);
            trie.addWordWithRatingAndDoPreCompute("DOLL", 11);
            trie.addWordWithRatingAndDoPreCompute("DONT", 21);
            trie.addWordWithRatingAndDoPreCompute("DART", 1);
            trie.addWordWithRatingAndDoPreCompute("DIP", 5);
            trie.addWordWithRatingAndDoPreCompute("DOLLAR", 51);
            trie.addWordWithRatingAndDoPreCompute("DOGE", 15);
            trie.addWordWithRatingAndDoPreCompute("OLD", 3);

            // Query the trie, without doing any pre-compute.
            {
                System.out.println("WITHOUT pre-compute answer : " + trie.wordsWithGivenPrefixWithoutPreCompute("D"));
                final long start = System.currentTimeMillis();
                // Assume we have made the request 10,000 times.
                for (int i = START; i <= TEN_THOUSAND_REQUESTS; i++) {
                    trie.wordsWithGivenPrefixWithoutPreCompute("D");
                }
                System.out.println("Time taken to run auto suggestion 10000 times WITHOUT pre-compute : [" + (System.currentTimeMillis() - start) + "] ms.\n");
            }

            // Pre compute already been done beforehand, while adding the words.

            // Query the trie, after pre-compute is done.
            {
                System.out.println("WITH pre-compute answer : " + trie.wordsWithGivenPrefixWithPreCompute("D"));
                final long start = System.currentTimeMillis();
                // Assume we have made the request 10,000 times.
                for (int i = START; i <= TEN_THOUSAND_REQUESTS; i++) {
                    trie.wordsWithGivenPrefixWithPreCompute("D");
                }
                System.out.println("Time taken to run auto suggestion 10000 times WITH pre-compute : [" + (System.currentTimeMillis() - start) + "] ms.\n");
            }

            System.out.println("Time taken to run pre-compute GOOD APPROACH : [" + (System.currentTimeMillis() - goodApproachStart) + "] ms.");
        }

    }
}
