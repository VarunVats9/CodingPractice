package com.practice.cses.sortingsearching;

import com.practice.fastio.InputReader;

import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class StickLengths {

    List<Integer> arr = null;

    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n = in.nextInt();
        arr = in.readIntegerArray(n);

        Collections.sort(arr);
        int l = 0, h = arr.size() - 1;
        while (l < h) {
            int m1 = l + (h - l) / 3;
            int m2 = l + 2 * (h - l) / 3;
            if (l == m1) {
                l = val(m1) > val(m2) ? m2 : m1;
                break;
            }
            if (val(m1) >= val(m2)) {
                l = m1;
            } else  {
                h = m2;
            }
        }

        out.println(val(l));
    }

    private long val(int m1) {
        long count = 0;
        for (int i = 0; i < arr.size(); i++) {
            count += Math.abs(arr.get(i) - arr.get(m1));
        }
        return count;
    }
}
