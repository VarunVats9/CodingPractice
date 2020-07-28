package com.practice.chelper;

import com.practice.fastio.InputReader;
import java.io.PrintWriter;

public class CMoveBrackets {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int t = in.nextInt();

        while (t-- > 0) {
            int n = in.nextInt();
            int o = 0, c = 0;
            String s = in.nextString();
            for (int i = 0; i < s.length(); i++) {
                if (s.charAt(i) == ')') {
                    if (o > 0) o--;
                    else c++;
                } else o++;
            }
            out.println(c);
        }
    }
}
