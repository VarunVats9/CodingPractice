package com.practice.codingblocks.practice.array;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Objects;

public class UniqueNumbersII {

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

            Integer f = null, s = null;

            for (int i = 0; i < 32; i++) {
                int count = 0;
                for (int j = 0; j < arr.length; j++) {
                    count += arr[j] & 1 << i;
                }
                // ith bit count of all the numbers.
                if ((count & 1) > 0) {
                    if (Objects.isNull(f)) {
                        // Lets find that number whose bit is set at ith pos.
                        for (int l = 0; l < arr.length; l++) {
                            if ((arr[l] & 1 << i) > 0) {
                                if (Objects.isNull(f)) {
                                    f = arr[l];
                                } else {
                                    f ^= arr[l];
                                }
                            }
                        }
                        break;
                    }
                }
            }
            s = f;
            for (int p = 0; p < arr.length; p++) {
                s ^= arr[p];
            }

            if (f > s) {
                int temp = s;
                s = f;
                f = temp;
            }

            System.out.println(f + " " + s);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
