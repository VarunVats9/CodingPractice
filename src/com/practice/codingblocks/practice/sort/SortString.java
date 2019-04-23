package com.practice.codingblocks.practice.sort;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

public class SortString {

    public static void main(String[] args) {

        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in))) {
            String line = bufferedReader.readLine();
            int m = Integer.parseInt(line);
            ArrayList<String> list = new ArrayList<>();
            for (int i = 0; i < m; i++) {
                line = bufferedReader.readLine();
                list.add(line);
            }

            Collections.sort(list, (a, b) -> {
                int min = Math.min(a.length(), b.length());
                for (int i = 0; i < min; i++) {
                    if (a.charAt(i) != b.charAt(i)) {
                        return Character.compare(a.charAt(i), b.charAt(i));
                    }
                }
                if (b.length() == min) {
                    return -1;
                }
                return 1;
            });

            for (String str : list)
                System.out.println(str);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
