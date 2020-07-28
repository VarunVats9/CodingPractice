package com.practice.cses.sortingsearching;

import com.practice.fastio.InputReader;
import com.practice.egork.lib.generated.collections.pair.IntIntPair;

import java.io.PrintWriter;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import static com.practice.egork.lib.collections.comparators.ComparatorUtils.sortIntAsc;


public class __TLE__ConcertTickets {

    List<Integer> tick = null;

    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n = in.nextInt(), m = in.nextInt();
        tick = in.readIntegerArray(n);
        List<Integer> per = in.readIntegerArray(m);

        Collections.sort(tick, sortIntAsc);

        for (int i = 0; i < m; i++) {
            if (tick.size() > 0) {
                IntIntPair pair = bSearch(per.get(i));
                if (pair.first != -1) {
                    tick.remove(pair.first);
                }
                out.println(pair.second);
            } else {
                out.println(-1);
            }
        }
    }

    private IntIntPair bSearch(Integer num) {
        int len = tick.size() - 1;
        if (num < tick.get(0)) return new IntIntPair(-1, -1);
        if (num >= tick.get(len)) return new IntIntPair(len, tick.get(len));

        int l = 0, h = len;
        while(l < h) {
            if (h == l+1) break;
            int m = (l + h)/2;
            if (Objects.equals(tick.get(m), num)) {
                return new IntIntPair(m, num);
            }
            if (tick.get(m) > num) {
                h = m;
            } else {
                l = m;
            }
        }

        return new IntIntPair(l, tick.get(l));
    }
}
