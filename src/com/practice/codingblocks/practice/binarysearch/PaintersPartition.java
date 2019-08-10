package com.practice.codingblocks.practice.binarysearch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class PaintersPartition {

    static int[] arr;
    static int k, n;
    static long max, min = Long.MIN_VALUE;

    public static void main(String[] args) {
        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in))) {
            String line = bufferedReader.readLine();
            k = Integer.parseInt(line);
            line = bufferedReader.readLine();
            n = Integer.parseInt(line);
            line = bufferedReader.readLine();
            String[] boards = line.split(" ");
            arr = new int[n];
            max = 0;
            for (int i = 0; i < boards.length; i++) {
                arr[i] = Integer.parseInt(boards[i]);
                max += arr[i];
                min = Math.max(min, arr[i]);
            }
            Arrays.sort(arr);
            System.out.println(partition());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static long partition() {
        long low = min;
        long high = max;

        while (low < high) {
            long mid = low + (high - low)/2;
            if (predicate(mid)) {
                high  = mid;
            } else {
                low = mid + 1;
            }
        }
        return low;
    }

    private static boolean predicate(long minimum) {
        int painterLeft = 1;
        long curr = 0;
        for (int i = 0; i < arr.length; i++) {
            curr += arr[i];
            if (curr > minimum) {
                painterLeft++;
                curr = arr[i];
            }
        }
        return painterLeft <= k;
    }
}
