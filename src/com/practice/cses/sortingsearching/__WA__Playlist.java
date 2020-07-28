package com.practice.cses.sortingsearching;

import com.practice.fastio.InputReader;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class __WA__Playlist {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n = in.nextInt();
        int[] arr = in.readIntArray(n);
        Map<Integer, Integer> map = new HashMap<>();

        int count = 0, min = 0;
        for (int i = 0; i < n; i++) {
            if (map.containsKey(arr[i])) {
                min = Math.max(map.get(arr[i]) + 1, min);
            } else {
                count = Math.max(count, i - min + 1);
            }
            map.put(arr[i], i);
        }

        out.println(count);
    }
}
