package com.practice.codejam19.round1C;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class Robot {

    private static char[][] arr = new char[255][500];

    private static String calculate(int n) {

        Set<Character> set = new HashSet<>();
        Set<Integer> remain = new HashSet<>();
        for (int i = 0; i < n; i++) {
            remain.add(i);
        }
        StringBuilder ans = new StringBuilder("");
        for (int i = 0; i < arr[0].length; i++) {
            set.clear();
            final int r = i;
            if (remain.size() == 0) break;
            remain.forEach(e -> set.add(arr[e][r]));
            if (set.size() == 3) return "IMPOSSIBLE";

            if (set.size() == 1) {
                ArrayList<Character> chars = new ArrayList<>(set);
                if (String.valueOf(chars.get(0)).equals("R")) {
                    ans.append("P");
                } else if (String.valueOf(chars.get(0)).equals("P")) {
                    ans.append("S");
                } else {
                    ans.append("R");
                }
                return ans.toString();
            } else if (set.size() == 2) {
                ArrayList<Character> chars = new ArrayList<>(set);
                if (String.valueOf(chars.get(0) + chars.get(1)).equals("RS") || String.valueOf(chars.get(0) + chars.get(1)).equals("SR")) {
                    ans.append("R");
                    Set<Integer> diff = remain.stream().filter(e -> arr[e][r] == 'S').collect(Collectors.toSet());
                    remain.removeAll(diff);
                } else if (String.valueOf(chars.get(0) + chars.get(1)).equals("SP") || String.valueOf(chars.get(0) + chars.get(1)).equals("PS")) {
                    ans.append("S");
                    Set<Integer> diff = remain.stream().filter(e -> arr[e][r] == 'P').collect(Collectors.toSet());
                    remain.removeAll(diff);
                } else if (String.valueOf(chars.get(0) + chars.get(1)).equals("RP") || String.valueOf(chars.get(0) + chars.get(1)).equals("PR")) {
                    ans.append("P");
                    Set<Integer> diff = remain.stream().filter(e -> arr[e][r] == 'R').collect(Collectors.toSet());
                    remain.removeAll(diff);
                }
            }
        }

        return ans.toString();
    }

    public static void main(String[] args) {
        try(BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in))) {
            String line = bufferedReader.readLine();
            int t = Integer.parseInt(line);
            for (int j = 1; j <= t; j++) {
                line = bufferedReader.readLine();
                int n = Integer.parseInt(line);
                for (int i = 0; i < n; i++) {
                    line = bufferedReader.readLine();
                    for (int k = 0, p = 0; p < 500;) {
                        arr[i][p] = line.charAt(k);
                        p++; k++;
                        if (k == line.length()) k = 0;
                    }
                }
                System.out.println("Case #" + j + ": " + calculate(n));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
