package com.practice.codingblocks.practice.math;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class UltraFast {

    public static void main(String[] args) {
        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in))) {
            String line = bufferedReader.readLine();
            Integer t = Integer.parseInt(line);
            while (t-- > 0) {
                line = bufferedReader.readLine();
                String mn[] = line.split(" ");
                String m = mn[0];
                String n = mn[1];
                System.out.println(xor(m, n));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String xor(final String m, final String n) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < m.length(); i++) {
            result.append((int)m.charAt(i) ^ (int)n.charAt(i));
        }
        return result.toString();
    }

}
