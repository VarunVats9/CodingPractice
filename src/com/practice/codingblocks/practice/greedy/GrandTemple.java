package com.practice.codingblocks.practice.greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.AbstractMap;
import java.util.ArrayList;

public class GrandTemple {

    public static void main(String[] args) {

        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in))) {
            String line = bufferedReader.readLine();
            int t = Integer.parseInt(line);
            ArrayList<AbstractMap.SimpleEntry<Integer, Integer>> list = new ArrayList<>();
            for (int i = 1; i <= t; i++) {
                line = bufferedReader.readLine();
                String[] xy = line.split(" ");
                int x = Integer.parseInt(xy[0]);
                int y = Integer.parseInt(xy[1]);
                list.add(new AbstractMap.SimpleEntry<>(x, y));
            }

            list.sort((a, b) -> a.getKey().compareTo(b.getKey()));

            int diffX = Integer.MIN_VALUE, diffY = Integer.MIN_VALUE;

            for (int j = 1; j < list.size(); j++) {
                int compare = list.get(j).getKey() - list.get(j-1).getKey();
                if (compare > diffX) {
                    diffX = compare;
                }
            }

            list.sort((a, b) -> a.getValue().compareTo(b.getValue()));

            for (int k = 1; k < list.size(); k++) {
                int compare = list.get(k).getValue() - list.get(k-1).getValue();
                if (compare > diffY) {
                    diffY = compare;
                }
            }

            System.out.println((diffX-1) * (diffY-1));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
