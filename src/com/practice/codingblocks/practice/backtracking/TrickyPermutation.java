package com.practice.codingblocks.practice.backtracking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

public class TrickyPermutation {

    private static ArrayList<String> arr = new ArrayList<>();

    private static void recursePermutation(char[] line, int l, int r) {

        if (l == r) {
            arr.add(String.valueOf(line));
        }

        for (int i = l; i <= r; i++) {
            if ((i > l && line[i] != line[l]) || (i == l && line[i] == line[l])) {
                swap(line, l, i);
                recursePermutation(line, l + 1, r);
                swap(line, l, i);
            }
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
            recursePermutation(line.toCharArray(), 0, line.length() - 1);
            Collections.sort(arr);
            arr.forEach(System.out::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
