package com.practice.codingblocks.practice.inclusionexclusion;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class NotSoEasy {

    private static int[] primes = new int[] {2, 3, 5, 7, 11, 13, 17, 19};
    private static long count = 0, res = 0, mul = 1;

    private static void checkSetBits(int num) {
        for (int i = 0; i < primes.length; i++) {
            if (((1 << i) & num) > 0) {
                count++;
                mul *= primes[i];
            }
        }
    }

    private static void incExc(long num) {
        res = 0;
        for (int i = 1; i <= ((1 << primes.length) - 1); i++) {
            count = 0;
            mul = 1;
            checkSetBits(i);
            res += (count & 1) > 0 ? num / mul : -1 * (num / mul);
        }
        System.out.println(res);
    }

    public static void main(String[] args) {

        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in))) {
            String line = bufferedReader.readLine();
            int t = Integer.parseInt(line);
            for (int i = 1; i <= t; i++) {
                line = bufferedReader.readLine();
                long num = Long.parseLong(line);
                checkSetBits((int)num);
                incExc(num);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
