package com.practice.codejam19.qualification;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Foregone {

    public static void main(String[] args) {
        try(BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in))) {
            String line = bufferedReader.readLine();
            int t = Integer.parseInt(line);
            for (int j = 1; j <= t; j++) {
                StringBuilder a = new StringBuilder("");
                StringBuilder b = new StringBuilder("");
                line = bufferedReader.readLine();
                for (int i = 0; i < line.length(); i++) {
                    char c = line.charAt(i);
                    if (Character.getNumericValue(c) == 4) {
                        a.append("3");
                        b.append("1");
                    } else {
                        a.append(c);
                        b.append("0");
                    }
                }
                while (b.charAt(0) == '0') {
                    b.deleteCharAt(0);
                }
                System.out.println("Case #" + j + ": " + a.toString() + " " + b.toString());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
