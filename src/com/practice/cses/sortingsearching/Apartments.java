package com.practice.cses.sortingsearching;

import com.practice.fastio.InputReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Apartments {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n = in.nextInt(), m = in.nextInt(), k = in.nextInt();

        List<Integer> a = in.readIntegerArray(n);
        List<Integer> b = in.readIntegerArray(m);
        Collections.sort(a);
        Collections.sort(b);

        int i = 0, j = 0, count = 0;
        int low = Math.max(a.get(i) - k, 0);
        int high = a.get(i) + k;
        while (j < m) {
            if (b.get(j) >= low && b.get(j) <= high) {
                count++;
                i++;
                j++;
                if (i >= n) break;
                low = Math.max(a.get(i) - k, 0);
                high = a.get(i) + k;
            } else if (b.get(j) < low) {
                j++;
            } else {
                i++;
                if (i >= n) break;
                low = Math.max(a.get(i) - k, 0);
                high = a.get(i) + k;
            }
        }

        out.println(count);
    }
}
