package com.practice.codingblocks.practice.recursion;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class SmartKeypadI {

    static String[] arr = new String[]{"", ".+@$", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};

    public static void main(String[] args) {
        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in))) {
            String line = bufferedReader.readLine();
            recurse(line, "");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void recurse(String line, String code) {
        if (line.length() == 0) {
            System.out.println(code);
            return;
        }
        int num = Integer.parseInt(line.substring(0, 1));
        for (int i = 0; i < arr[num].length(); i++) {
            recurse(line.substring(1), code + arr[num].charAt(i));
        }
    }
}
