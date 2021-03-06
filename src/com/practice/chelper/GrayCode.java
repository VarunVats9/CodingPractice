package com.practice.chelper;

import com.practice.fastio.InputReader;
import java.io.PrintWriter;

public class GrayCode {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n = in.nextInt();
        for (int i = 0; i < 1 << n; i++) {
            int val = i ^ i >> 1;
            String ans = Integer.toBinaryString(val);
            while (ans.length() != n)
                ans = "0" + ans;
            out.println(ans);
        }
    }
}
