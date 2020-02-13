package com.practice.atcoder.algoskill;

import java.util.Scanner;
import java.io.PrintWriter;

public class AIsItANumber {
    public void solve(int testNumber, Scanner in, PrintWriter out) {
        String s = in.nextLine();
        int ans = 0;

        for (int i = 0; i < s.length(); i++) {
            if (!Character.isDigit(s.charAt(i))) {
                out.println("error");
                return;
            }
            ans = ans*10 + (s.charAt(i) - '0');
        }

        out.println(2*ans);
    }
}
