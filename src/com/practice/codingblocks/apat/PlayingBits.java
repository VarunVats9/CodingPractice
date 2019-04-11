package com.practice.codingblocks.apat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class PlayingBits {

    public static void main(String[] args) {

        try(BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in))) {
            String line = bufferedReader.readLine();
            int q = Integer.parseInt(line);
            while (q-- > 0) {
                line = bufferedReader.readLine();
                String[] str = line.split(" ");
                int a = Integer.parseInt(str[0]);
                int b = Integer.parseInt(str[1]);
                int count = 0;
                for (int i = a; i <= b; i++) {
                    count += countBits(i);
                }
                System.out.println(count);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static int countBits(int num) {
        int count = 0;
        while (num > 0) {
            num = num & (num - 1);
            count++;
        }
        return count;
    }
}
