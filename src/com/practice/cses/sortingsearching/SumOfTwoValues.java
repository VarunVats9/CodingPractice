package com.practice.cses.sortingsearching;

import com.practice.fastio.InputReader;
import com.practice.egork.lib.generated.collections.pair.IntIntPair;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static com.practice.egork.lib.collections.comparators.ComparatorUtils.sortIntIntPairAsc;

public class SumOfTwoValues {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n = in.readInt(), x = in.readInt();
        List<IntIntPair> arr = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            arr.add(new IntIntPair(i+1, in.readInt()));
        }

        Collections.sort(arr, sortIntIntPairAsc);

        int l = 0, h = n-1;
        while (l < h) {
            if (arr.get(l).second + arr.get(h).second == x) {
                out.println(arr.get(l).first + " " + arr.get(h).first);
                return;
            } else if (arr.get(l).second + arr.get(h).second < x) {
                l++;
            } else {
                h--;
            }
        }

        out.println("IMPOSSIBLE");
    }
}
