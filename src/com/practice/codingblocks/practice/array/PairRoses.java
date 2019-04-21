package com.practice.codingblocks.practice.array;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class PairRoses {

    private static Map<Integer, Boolean> map = new HashMap<>();

    public static void main(String[] args) {

        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in))) {
            String line = bufferedReader.readLine();
            int t = Integer.parseInt(line);
            for (int i = 1; i <= t; i++) {
                map.clear();
                ArrayList<Integer> arr = new ArrayList<>();
                line = bufferedReader.readLine();
                int q = Integer.parseInt(line);
                line = bufferedReader.readLine();
                String[] str = line.split(" ");
                for (int j = 0; j < str.length; j++) {
                    int x = Integer.parseInt(str[j]);
                    arr.add(x);
                }
                line = bufferedReader.readLine();
                int m = Integer.parseInt(line);

                int diff = Integer.MAX_VALUE;
                int s = 0, g = 0;
                for (int k = 0; k < q; k++) {
                    if (map.containsKey(arr.get(k)) && diff > Math.abs(m - 2*arr.get(k))) {
                        s = arr.get(k);
                        g = m - arr.get(k);
                        diff = Math.abs(s - g);
                    } else if (!map.containsKey(arr.get(k))) {
                        map.put(m - arr.get(k), true);
                    }
                }

                if (s > g) {
                    int temp = g;
                    g = s;
                    s = temp;
                }
                System.out.println("Deepak should buy roses whose prices are " + s + " and " + g + ".");
                line = bufferedReader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
