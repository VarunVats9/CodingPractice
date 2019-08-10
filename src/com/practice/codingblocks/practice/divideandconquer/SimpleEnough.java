package com.practice.codingblocks.practice.divideandconquer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SimpleEnough {

    private static Map<Long, List<Boolean>> map;

    public static void main(String[] args) {
        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in))) {
            String line = bufferedReader.readLine();
            map = new HashMap<>();
            String[] nums = line.split(" ");
            long num = Long.parseLong(nums[0]);
            int l = Integer.parseInt(nums[1]);
            int r = Integer.parseInt(nums[2]);
            List<Boolean> list = solve(num);
            int count = 0;
            for (int i = l-1; i <= r-1; i++) {
                count += list.get(i) ? 1 : 0;
            }
            System.out.println(count);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static List<Boolean> solve(long num) {
        if (map.containsKey(num)) {
            return map.get(num);
        }

        if (num == 1 || num == 0) {
            List<Boolean> list = new ArrayList<>();
            list.add(num == 1);
            return list;
        }

        long a = (long)Math.floor(num/2);
        long b = num % 2;

        List<Boolean> list = new ArrayList<>();
        list.addAll(solve(a));
        list.addAll(solve(b));
        list.addAll(solve(a));

        map.put(num, list);
        return list;
    }
}
