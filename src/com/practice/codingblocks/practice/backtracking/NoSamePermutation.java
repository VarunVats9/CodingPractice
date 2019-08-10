package com.practice.codingblocks.practice.backtracking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class NoSamePermutation {

    private static List<String> list;

    public static void main(String[] args) {
        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in))) {
            String line = bufferedReader.readLine();
            int n = Integer.parseInt(line);
            int[] arr = new int[n];
            line = bufferedReader.readLine();
            String[] num = line.split(" ");
            for (int i = 0; i < n; i++) {
                arr[i] = Integer.parseInt(num[i]);
            }
            list = new ArrayList<>();
            backtrack(arr, 0);
            Collections.sort(list);
            list.forEach(System.out::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void backtrack(int[] arr, int idx) {
        if (idx >= arr.length - 1) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < arr.length; i++) {
                sb.append(arr[i]).append(" ");
            }
            sb.deleteCharAt(sb.length() - 1);
            list.add(sb.toString());
            return;
        }

        for (int i = idx; i < arr.length; i++) {
            if (arr[idx] == arr[i] && i > idx) {
                continue;
            }
            swap(arr, i, idx);
            backtrack(arr, idx + 1);
            swap(arr, idx, i);
        }
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
