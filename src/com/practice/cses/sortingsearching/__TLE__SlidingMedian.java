package com.practice.cses.sortingsearching;

import com.practice.egork.lib.search.LowerBound;
import com.practice.fastio.InputReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class __TLE__SlidingMedian {

    List<Integer> list = null;
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n = in.nextInt();
        int k = in.nextInt();

        int[] arr = in.readIntArray(n);

        List<Integer> list = new ArrayList<>(k);
        for (int i = 0; i < k; i++) {
            list.add(arr[i]);
        }
        Collections.sort(list);
        out.print(list.get((k-1)/2) + " ");

        for (int i = k; i < n; i++) {
            list.remove(Integer.valueOf(arr[i - k]));
            int idx = LowerBound.lowerBoundInteger(list, k-1, arr[i]);
            list.add(idx, arr[i]);
            out.print(list.get((k-1)/2) + " ");
        }
    }
}
