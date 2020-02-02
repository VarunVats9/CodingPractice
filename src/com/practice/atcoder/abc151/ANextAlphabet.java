package com.practice.atcoder.abc151;

import java.util.Scanner;
import java.io.PrintWriter;

public class ANextAlphabet {
    public void solve(int testNumber, Scanner in, PrintWriter out) {
        String c = in.next();
        out.println((char)(c.charAt(0) + 1));
    }
}
