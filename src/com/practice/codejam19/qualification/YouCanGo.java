package com.practice.codejam19.qualification;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class YouCanGo {

    public static void main(String[] args) {
        try(BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in))) {
            String line = bufferedReader.readLine();
            int t = Integer.parseInt(line);
            for (int j = 1; j <= t; j++) {
                line = bufferedReader.readLine();
                StringBuilder a = new StringBuilder("");
                line = bufferedReader.readLine();
                for (int i = 0; i < line.length(); i++) {
                    if (line.charAt(i) == 'S') {
                        a.append("E");
                    } else {
                        a.append("S");
                    }
                }
                System.out.println("Case #" + j + ": " + a.toString());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
