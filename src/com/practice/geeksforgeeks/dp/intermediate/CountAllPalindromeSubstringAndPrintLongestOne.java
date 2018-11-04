package com.practice.geeksforgeeks.dp.intermediate;

import com.practice.emaxx.geometry.Pair;

/**
 * Created by vvats on 04/11/18.
 */
public class CountAllPalindromeSubstringAndPrintLongestOne {

    static private int count(final String target, final int n) {

        int count = 0;
        boolean[][] dp = new boolean[n][n];
        Pair<Integer, Integer> pair = new Pair<>(Integer.MIN_VALUE, Integer.MIN_VALUE);
        int maxPalindromeLength = 0;

        for (int j = 1; j <= n; j++) {
            for (int i = 0; i <= n-j; i++) {
                int k = i+j-1;
                if (j <= 2) {
                    dp[i][k] = target.charAt(i) == target.charAt(k);
                } else {
                    dp[i][k] = target.charAt(i) == target.charAt(k) && dp[i+1][k-1];
                }
                if (dp[i][k]) {
                    count++;
                    if (maxPalindromeLength < j) {
                        maxPalindromeLength = j;
                        pair.setLeft(i);
                        pair.setRight(k);
                    }

                }
            }
        }

        if (pair.getLeft() != Integer.MIN_VALUE) {
            System.out.println("\nLongest Palindrome is : " + target.substring(pair.getLeft(), pair.getRight() + 1));
        }
        return count - n;
    }


    public static void main(String[] args) {

        {
            final String target = "abaab";
            System.out.println("Count of all the palindrome substring : " + CountAllPalindromeSubstringAndPrintLongestOne.count(target, target.length()));
        }

        {
            final String target = "varat";
            System.out.println("Count of all the palindrome substring : " + CountAllPalindromeSubstringAndPrintLongestOne.count(target, target.length()));
        }
    }

}
