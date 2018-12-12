package com.practice.hackerrank.interviewpreparationkit.strings;

import java.util.HashSet;
import java.util.Set;

/**
 * Date : 07 Dec, 2018
 * Time : 8:18 PM
 *
 * @author : Varun Vats (varunvats32@gmail.com)
 */
public class StringManipulation {

    /*
     * Question : The string has only two characters, A and B, find the minimum deletions
     * required to make it a alternating character string.
     */
    static int alternatingCharacters(String s) {
        int count = 0;
        for (int i = 0; i < s.length() - 1; i++) {
            if (s.charAt(i) == s.charAt(i+1)) {
                count++;
            }
        }
        return count;
    }

    /*
     * Question : Find the minimum chars to be deleted from two strings, to make common anagram
     * from both.
     */
    static int makeAnagram(String a, String b) {
        if (a.length() > b.length()) {
            return makeAnagram(b, a);
        }

        int[] alphaArray = new int[26];

        int common = 0;
        for (int i = 0; i < a.length(); i++) {
            alphaArray[a.charAt(i) - 'a']++;
        }

        for (int i = 0; i < b.length(); i++) {
            int idx = b.charAt(i) - 'a';
            if (alphaArray[idx] == 0) {
                continue;
            }
            alphaArray[idx]--;
            common++;
        }

        return a.length() + b.length() - 2 * common;
    }

    /*
     * Question : Find if the string is valid, wherein if you delete just one char at maximum, would
     * result in all the unique chars to be of same counts.
     */
    static String isValid(String s) {
        int[] alphaArray = new int[26];
        String ans = "NO";

        for (int i = 0; i < s.length(); i++) {
            alphaArray[s.charAt(i) - 'a']++;
        }

        for (int i = -1; i < alphaArray.length; i++) {
            if (i >= 0 && alphaArray[i] == 0) {
                continue;
            }

            if (i >= 0) {
                alphaArray[i]--;
            }

            Set<Integer> set = new HashSet<>();
            for (int j = 0; j < alphaArray.length; j++) {
                if (alphaArray[j] > 0) {
                    set.add(alphaArray[j]);
                }
            }

            if (set.size() == 1) {
                ans = "YES";
            }

            if (i >= 0) {
                alphaArray[i]++;
            }
        }

        return ans;
    }

    /*
     * Question : Find maximum common word that can be created, by deleting the minimum words from
     * both the strings. But you cannot rearrange the chars.
     *
     * Same as LCS.
     */
    static int commonChild(String s1, String s2) {
        int n = s1.length();
        int[][] dp = new int[n+1][n+1];

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if (s1.charAt(i-1) == s2.charAt(j-1)) {
                    dp[i][j] = 1 + dp[i-1][j-1];
                } else {
                    dp[i][j] = Math.max(dp[i][j-1], dp[i-1][j]);
                }
            }
        }

        return dp[n][n];
    }

    /*
     * Question : Find maximum maximum number of special palindromes, that can be
     * formed from String s. For palindrome, either all chars are same or else just the
     * middle element is different.
     */
    static long substrCount(int n, String s) {
        boolean[][] dp = new boolean[n+1][n+1];

        int count = 0;

        for (int l = 1; l <= n; l++) {
            for (int i = 0; i < n; i++) {
                int j = i+l-1;

                if (j >= n) {
                    continue;
                }

                if (l == 1) {
                    dp[i][j] = true;
                    //System.out.println(i + " " + j);
                    if (dp[i][j]) count++;
                    continue;
                }

                if (l == 2 && s.charAt(i) == s.charAt(j)) {
                    dp[i][j] = true;
                    //System.out.println(i + " " + j);
                    if (dp[i][j]) count++;
                    continue;
                }

                if (l == 3) {
                    dp[i][j] = s.charAt(i) == s.charAt(j) && dp[i+1][j-1];
                    //System.out.println(i + " " + j);
                    if (dp[i][j]) count++;
                    continue;
                }

                dp[i][j] = s.charAt(i) == s.charAt(j) && dp[i+1][j-1] && s.charAt(i) == s.charAt(i+1);
                //System.out.println(i + " " + j);
                if (dp[i][j]) count++;
            }
        }
        return count;
    }

    public static void main(String[] args) {

        {
            System.out.println(StringManipulation.alternatingCharacters("AAAA"));
            System.out.println(StringManipulation.alternatingCharacters("BABABA"));
            System.out.println(StringManipulation.alternatingCharacters("AAABBB"));
        }

        {
            System.out.println(StringManipulation.makeAnagram("def", "abc"));
        }

        {
            System.out.println(StringManipulation.isValid("aabbc"));
            System.out.println(StringManipulation.isValid("aaabbcc"));
            System.out.println(StringManipulation.isValid("abc"));
            System.out.println(StringManipulation.isValid("abcdefghhgfedecba"));
        }

        {
            System.out.println(StringManipulation.commonChild("ABCDEF", "FBDAMN"));
        }

        {
            System.out.println(StringManipulation.substrCount(5, "asasd"));
            System.out.println(StringManipulation.substrCount(7, "abcbaba"));
            System.out.println(StringManipulation.substrCount(4, "aaaa"));
        }
    }
}
