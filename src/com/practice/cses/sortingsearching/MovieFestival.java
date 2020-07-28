package com.practice.cses.sortingsearching;

import com.practice.fastio.InputReader;
import com.practice.egork.lib.generated.collections.pair.IntIntPair;

import java.io.PrintWriter;
import java.util.Collections;
import java.util.List;

public class MovieFestival {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n = in.nextInt();
        List<IntIntPair> mov = in.readIntPairArray(n);
        Collections.sort(mov, (a, b) -> {
            if (a.second > b.second) return 1;
            else if (a.second < b.second) return -1;
            else return 0;
        });

        int count = 0;
        int prev = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            if (mov.get(i).first >= prev) {
                count++;
                prev = mov.get(i).second;
            }
        }

        out.println(count);
    }
}
