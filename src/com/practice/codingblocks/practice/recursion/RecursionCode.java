package com.practice.codingblocks.practice.recursion;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class RecursionCode {

    private static ArrayList<String> arr = new ArrayList<>();

    private static void recurseCode(String original, String code, int idx) {

        if (idx >= original.length()) {
            arr.add(code);
            return;
        }

        String f = "" + original.charAt(idx);
        if (isValid(f)) {
            recurseCode(original, code + (char)('a' + (Integer.parseInt(f) - 1)), idx + 1);
        }

        if (idx + 1 < original.length()) {
            String s = "" + original.charAt(idx) + original.charAt(idx + 1);
            if (isValid(s)) {
                recurseCode(original, code + (char)('a' + (Integer.parseInt(s) - 1)), idx + 2);
            }
        }
    }

    private static boolean isValid(String code) {
        if (code.charAt(0) == '0') return false;
        if (code.length() == 1) {
            return true;
        }
        int num = (code.charAt(0) - '0') * 10 + code.charAt(1) - '0';
        return num <= 26;
    }

    public static void main(String[] args) {

        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in))) {
            String line = bufferedReader.readLine();
            recurseCode(line, "", 0);
            System.out.println(arr);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
