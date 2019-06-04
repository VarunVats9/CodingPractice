package com.practice.codingblocks.practice.math;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class PlayingWithBits {

    public static void main(String[] args) {
        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in))) {
            String line = bufferedReader.readLine();
            Integer t = Integer.parseInt(line);
            while (t-- > 0) {
                line = bufferedReader.readLine();
                String mn[] = line.split(" ");
                int m = Integer.parseInt(mn[0]);
                int n = Integer.parseInt(mn[1]);
                long count = 0;
                for (int i = m; i <= n; i++) {
                    int num = i;
                    while (num > 0) {
                        count += 1;
                        num = num & num-1;
                    }
                }
                System.out.println(count);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
