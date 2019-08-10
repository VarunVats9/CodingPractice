package com.practice.codingblocks.practice.backtracking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.LinkedList;

public class SumItUp {

    static public int sum;
    static public int[] arr;
    static public HashSet<LinkedList<Integer>> set;

    public static void main(String[] args) {
        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in))) {
            String line = bufferedReader.readLine();
            int n = Integer.parseInt(line);
            arr = new int[n];
            line = bufferedReader.readLine();
            String[] num = line.split(" ");
            for (int i = 0; i < n; i++) {
                arr[i] = Integer.parseInt(num[i]);
            }
            Arrays.sort(arr);
            line = bufferedReader.readLine();
            sum = Integer.parseInt(line);
            set = new LinkedHashSet<>();
            LinkedList<Integer> list = new LinkedList<>();
            backtrackSum(list, 0, 0);
            set.forEach(e -> {
                for (Integer i : e) {
                    System.out.print(i + " ");
                }
                System.out.println();
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void backtrackSum(LinkedList<Integer> list, int idx, int total) {
        if (idx >= arr.length) {
            return;
        }
        if (total == sum) {
            set.add(new LinkedList<>(list));
            return;
        }
        list.addLast(arr[idx]);
        backtrackSum(list, idx+1, total + arr[idx]);
        list.removeLast();
        backtrackSum(list, idx+1, total);
    }
}
