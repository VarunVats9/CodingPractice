package com.practice.codingblocks.practice.binarysearch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class AggressiveCows {

    static int[] arr;
    static int cows;

    public static void main(String[] args) {
        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in))) {
            String line = bufferedReader.readLine();
            String[] nums = line.split(" ");
            int n = Integer.parseInt(nums[0]);
            int c = Integer.parseInt(nums[1]);
            arr = new int[n];
            cows = c;
            line = bufferedReader.readLine();
            String[] stalls = line.split(" ");
            for (int i = 0; i < stalls.length; i++) {
                arr[i] = Integer.parseInt(stalls[i]);
            }
            Arrays.sort(arr);
            System.out.println(binarySearch());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static int binarySearch() {
        int low = 0;
        int end = arr[arr.length - 1];
        while (end - low > 1) {
            int mid = (low + end) / 2;
            if (predicate(mid)) {
                low = mid;
            } else {
                end = mid;
            }
        }
        return low;
    }

    private static boolean predicate(int min) {
        int cowsLeft = cows-1;
        int prev = 0, curr = 1;
        while (cowsLeft > 0 && curr < arr.length) {
            if (arr[curr] - arr[prev] >= min) {
                prev = curr;
                cowsLeft--;
            }
            curr++;
        }
        if (cowsLeft > 0) {
            return false;
        }
        return true;
    }
}
