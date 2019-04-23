package com.practice.codingblocks.practice.recursion;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Dictionary {

    private static String word;

    private static void recurseAll(char[] arr, int level) {

        if (word.compareTo(Arrays.toString(arr)) < 0) {
            System.out.println(arr);
        }

        if (level == 0) {
            recurseAll(arr, level+1);
        }

        for (int i = level+1; i < arr.length; i++) {
            swap(arr, level, i);
            recurseAll(arr, level+1);
            swap(arr, level, i);
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
            word = Arrays.toString(arr);
            recurseAll(arr, 0);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
