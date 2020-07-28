package com.practice.chelper;

import com.practice.fastio.InputReader;
import java.io.PrintWriter;

public class BMultiplyBy2DivideBy6 {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n = in.nextInt();

        for (int i = 0; i < n; i++) {
            int x = in.nextInt();
            int count = 0;
            boolean notDivisible = false;
            while (x != 1) {
                if (x % 3 != 0) {
                    notDivisible = true;
                    break;
                }
                if (x % 6 != 0) {
                    x *= 2;
                } else {
                    x /= 6;
                }
                count++;
            }

            out.println(notDivisible ? -1 : count);
        }
    }
}
