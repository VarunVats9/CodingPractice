package com.practice.cses.sortingsearching;

import com.practice.egork.lib.collections.intcollection.Heap;
import com.practice.egork.lib.generated.collections.pair.CharIntPair;
import com.practice.egork.lib.generated.collections.pair.IntIntPair;
import com.practice.egork.lib.generated.collections.pair.StringIntPair;
import com.practice.egork.lib.search.UpperBound;
import com.practice.fastio.InputReader;
import java.io.PrintWriter;
import java.util.*;

public class __WA__TrafficLights {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int x = in.nextInt(), n = in.nextInt();
        //TreeSet<Integer>
        PriorityQueue<StringIntPair> pq = new PriorityQueue<>(StringIntPair.sortCharIntPairDesc);
        pq.add(new StringIntPair("1" + "," + x, x));

        for (int i = 0; i < n; i++) {
            int y = in.nextInt();
//            int s = l.size();
//            int idx = UpperBound.upperBoundInteger(l, s, y);
//            if (idx > 0 && idx != s) {
//
//            }
            out.println(pq.peek().second);
        }
    }
}
