package com.practice.hackerrank.interviewpreparationkit;

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

    static String isValid(String s) {
        int[] alphaArray = new int[26];
        int mx1 = 0, mx2 = 0;
        for (int i = 0; i < s.length(); i++) {
            int idx = s.charAt(i) - 'a';
            alphaArray[idx]++;
            if (alphaArray[idx] > mx1) {
                mx2 = mx1;
                mx1 = alphaArray[idx];
            } else if (alphaArray[idx] > mx2 && alphaArray[idx] != mx1) {
                mx2 = alphaArray[idx];
            }
        }

        if (mx1 != mx2+1) {
            return "NO";
        }

        int countMx1 = 0, countMx2 = 0, countTotal = 0;
        for (int i = 0; i < alphaArray.length; i++) {
            if (alphaArray[i] == 0) {
                continue;
            }

            if (alphaArray[i] == mx1) {
                countMx1++;
            }

            if (alphaArray[i] == mx2) {
                countMx2++;
            }

            countTotal++;
        }

        if (countTotal != countMx1 + countMx2) {
            return "NO";
        }

        if (countMx1 == countTotal) {
            return "YES";
        }

        if (countMx2 == 1) {
            return "YES";
        }

        if (countMx1 > 1 || Math.abs(countMx1 - countMx2) > 1) {
            return "NO";
        }

        return "YES";
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
        }
    }
}
