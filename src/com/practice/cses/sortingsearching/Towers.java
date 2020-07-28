package com.practice.cses.sortingsearching;

import com.practice.egork.lib.search.UpperBound;
import com.practice.fastio.InputReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class Towers {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n = in.nextInt();
        int[] arr = in.readIntArray(n);
        List<Integer> vec = new ArrayList<>(n);

        for (int i = 0; i < n; i++) {
            int s = vec.size();
            int idx = UpperBound.upperBoundInteger(vec, s, arr[i]);
            if (idx == s) {
                vec.add(arr[i]);
            } else {
                vec.set(idx, arr[i]);
            }
        }

        out.println(vec.size());
    }
}
