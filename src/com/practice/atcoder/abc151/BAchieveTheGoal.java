package com.practice.atcoder.abc151;

import java.util.Scanner;
import java.io.PrintWriter;

public class BAchieveTheGoal {
    public void solve(int testNumber, Scanner in, PrintWriter out) {
        int N = in.nextInt();
        int K = in.nextInt();
        int M = in.nextInt();

        int ans = M*N;

        for (int i = 1; i < N; i++) {
            ans -= in.nextInt();
        }

        if (ans <= 0) {
            out.println(0);
            return;
        }

        if (ans > K) {
            out.println(-1);
            return;
        }

        if (ans <= K) {
            out.println(ans);
        }
    }
}
