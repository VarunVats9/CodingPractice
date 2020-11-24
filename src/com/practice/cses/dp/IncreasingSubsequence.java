package com.practice.cses.dp;

import com.practice.egork.lib.search.LowerBound;
import com.practice.fastio.InputReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;

public class IncreasingSubsequence {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n = in.nextInt();
        int[] arr = in.nextIntArray(n);

        ArrayList<Integer> res = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            int lower = LowerBound.lowerBoundInteger(res, res.size(), arr[i]);
            if (lower == res.size()) {
                res.add(arr[i]);
            } else {
                res.set(lower, arr[i]);
            }
        }
        out.println(res.size());
    }
}
