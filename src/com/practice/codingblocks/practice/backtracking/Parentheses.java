package com.practice.codingblocks.practice.backtracking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

public class Parentheses {

    private static ArrayList<String> arr = new ArrayList<>();

    private static void createParenth(int n, String s, int o, int c) {
        if (s.length() > n || c > o) {
            return;
        }

        if (s.length() == n && c == o) {
            arr.add(s);
        }

        createParenth(n, s + "(", o+1, c);
        createParenth(n, s + ")", o, c+1);
    }

    public static void main(String[] args) {

        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in))) {
            String line = bufferedReader.readLine();
            int n = 2 * Integer.parseInt(line);
            createParenth(n, "", 0, 0);

            Collections.sort(arr, (s1, s2) -> {
                for (int i = 0; i < n; i++) {
                    if (s1.charAt(i) != s2.charAt(i)) {
                        if (s1.charAt(i) == '(') {
                            return 1;
                        }
                        return -1;
                    }
                }
                return 0;
            });

            arr.forEach(System.out::println);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
