package com.practice.codingblocks.practice.dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Mixtures {

    private static int[] arr;

    private static int recursiveMix(int idx, int prev, int curr, int count, List<Integer> list) {

        if (list.size() == 1) {
            return 0;
        }

        int max = Integer.MAX_VALUE;

        for (int i = 1; i < list.size() - 1; i++) {
            //int back = prev * arr[idx] + recursiveMix(idx+1, (arr[idx] + prev) % 100, arr[], count+1);
            //int forw = arr[idx] * arr[idx+1] + recursiveMix(idx+2, (arr[idx] + arr[idx+1]) % 100, count+1);
            //max = Math.min(max, Math.min(back, forw));
        }

        return max;
    }

    public static void main(String[] args) {
        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in))) {
            String line = bufferedReader.readLine();
            int n = Integer.parseInt(line);
            line = bufferedReader.readLine();
            String[] str = line.split(" ");
            arr = new int[n];
            for (int i = 0; i < n; i++) {
                arr[i] = Integer.parseInt(str[i]);
            }
            System.out.println(recursiveMix(1, arr[0], arr[1], 0, new ArrayList<>()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
