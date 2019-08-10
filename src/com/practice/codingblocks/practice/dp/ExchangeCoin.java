package com.practice.codingblocks.practice.dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

public class ExchangeCoin {

    private static HashMap<Integer, Long> map = new HashMap<>();

    private static long dpCoin(int n) {
        if (n < 1) {
            return 0;
        }

        if (map.containsKey(n)) {
            return map.get(n);
        }

        long ans = Math.max(n, dpCoin(n/3) + dpCoin(n/2) + dpCoin(n/4));
        map.put(n, ans);
        return ans;
    }

    public static void main(String[] args) {

        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in))) {
            String line = bufferedReader.readLine();
            int n = Integer.parseInt(line);
            System.out.println(dpCoin(n));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
