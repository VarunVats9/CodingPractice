package com.practice.atcoder.abc155;

import java.util.*;
import java.io.PrintWriter;

public class CPoll {
    public void solve(int testNumber, Scanner in, PrintWriter out) {
        int n = in.nextInt();
        List<String> list = new ArrayList<>();
        Map<String, Integer> map = new HashMap<>();

        in.nextLine();
        int max = 0;
        for (int i = 1; i <= n; i++) {
            String s = in.nextLine();
            if (!map.containsKey(s)) {
                map.put(s, 0);
            }
            int count = map.get(s) + 1;
            max = Math.max(count, max);
            map.put(s, map.get(s) + 1);
        }

        for (String s : map.keySet()) {
            if (map.get(s) == max) {
                list.add(s);
            }
        }

        Collections.sort(list);

        for (int i = 0; i < list.size(); i++) {
            out.println(list.get(i));
        }
    }
}
