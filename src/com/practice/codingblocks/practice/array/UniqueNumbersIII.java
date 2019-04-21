package com.practice.codingblocks.practice.array;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class UniqueNumbersIII {

    public static void main(String[] args) {

        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in))) {
            String line = bufferedReader.readLine();
            int t = Integer.parseInt(line);
            int[] arr = new int[t];
            line = bufferedReader.readLine();
            String[] num = line.split(" ");
            for (int i = 0; i < num.length; i++) {
                int x = Integer.parseInt(num[i]);
                arr[i] = x;
            }

            Integer f = 0;

            for (int i = 0; i < 32; i++) {
                int count = 0;
                for (int j = 0; j < arr.length; j++) {
                    count += arr[j] & 1 << i;
                }
                // ith bit count of all the numbers.
                if (count % 3 > 0) {
                    f += 1 << i;
                }
            }
            System.out.println(f);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
