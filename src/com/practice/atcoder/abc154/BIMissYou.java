package com.practice.atcoder.abc154;

import java.util.Scanner;
import java.io.PrintWriter;

public class BIMissYou {
    public void solve(int testNumber, Scanner in, PrintWriter out) {
        String s = in.nextLine();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            sb.append("x");
        }
        out.println(sb.toString());
    }
}
