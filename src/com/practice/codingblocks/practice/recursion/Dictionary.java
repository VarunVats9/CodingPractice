package com.practice.codingblocks.practice.recursion;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class Dictionary {

    private static String word;
    private static Set<String> dict = new HashSet<>();
    private static ArrayList<String> dictionary = new ArrayList<>();

    private static void recurseAll(char[] arr, int l, int r) {

        if (word.compareTo(Arrays.toString(arr)) < 0 && l == r) {
            dict.add(String.valueOf(arr));
        }

        for (int i = l; i <= r; i++) {
            swap(arr, l, i);
            recurseAll(arr, l+1, r);
            swap(arr, l, i);
        }
    }

    private static void swap(char[] arr, int i, int j) {
        char temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void main(String[] args) {

        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in))) {
            String line = bufferedReader.readLine();
            char[] arr = line.toCharArray();
            int n = arr.length;
            word = Arrays.toString(arr);
            recurseAll(arr, 0, n-1);

            dictionary.addAll(dict);
            Collections.sort(dictionary);

            for (int i = 0; i < dictionary.size(); i++) {
                System.out.println(dictionary.get(i));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
