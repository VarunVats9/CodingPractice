package com.practice.codingblocks.apat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.LinkedList;

public class ReplacementAlgorithm {

    private static HashMap<Integer, Pairs> map = new HashMap<>();
    private static LinkedList<Pairs> list = new LinkedList<>();
    private static int c = 0;

    private static void set(int x, int y) {
        if (list.size() == c) {
            Pairs p = list.remove(0);
            map.remove(p.key);
        }

        Pairs p = new Pairs(x, y);
        list.addLast(p);
        map.put(p.key, p);
    }

    private static int get(int x) {
        if (!map.containsKey(x)) {
            return -1;
        }
        return map.get(x).value;
    }

    private static class Pairs {
        public int key, value;
        public Pairs(int x, int y) {
            this.key = x;
            this.value = y;
        }
    }

    public static void main(String[] args) {

        try(BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in))) {
            String line = bufferedReader.readLine();
            int t = Integer.parseInt(line);
            while (t-- > 0) {
                map.clear();
                list.clear();
                line = bufferedReader.readLine();
                c = Integer.parseInt(line);
                line = bufferedReader.readLine();
                int q = Integer.parseInt(line);
                line = bufferedReader.readLine();
                String[] str = line.trim().split(" ");
                for (int i = 0; i < str.length; i++) {
                    if (str[i].equals("SET")) {
                        int x = Integer.parseInt(str[++i]);
                        int y = Integer.parseInt(str[++i]);
                        set(x, y);
                    } else {
                        int x = Integer.parseInt(str[++i]);
                        System.out.print(get(x) + " ");
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
