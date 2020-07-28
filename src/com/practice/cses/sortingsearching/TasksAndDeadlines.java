package com.practice.cses.sortingsearching;

import com.practice.egork.lib.generated.collections.pair.IntIntPair;
import com.practice.fastio.InputReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class TasksAndDeadlines {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n = in.nextInt();
        List<IntIntPair> list = new ArrayList<>();
        for (int i = 0; i < n; i++) {
           list.add(IntIntPair.makePair(in.nextInt(), in.nextInt()));
        }

        Collections.sort(list, Comparator.comparingInt(a -> a.first));

        long prev = 0, opt = 0;
        for (int i = 0; i < n; i++) {
            prev += list.get(i).first;
            opt += list.get(i).second - prev;
        }

        out.println(opt);
    }
}
