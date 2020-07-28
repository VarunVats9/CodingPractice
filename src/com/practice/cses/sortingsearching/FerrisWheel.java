package com.practice.cses.sortingsearching;

import com.practice.fastio.InputReader;
import java.io.PrintWriter;
import java.util.Collections;
import java.util.List;

public class FerrisWheel {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n = in.nextInt(), x = in.nextInt();
        List<Integer> arr = in.readIntegerArray(n);
        Collections.sort(arr);

        int l = 0, h = n-1, count = 0;
        while (l <= h) {
            if (arr.get(l) + arr.get(h) <= x) {
                l++; h--;
            } else {
                h--;
            }
            count++;
        }

        out.println(count);
    }
}
