package com.practice.cses.introductoryproblems;

import java.util.*;
import java.io.PrintWriter;

public class CreatingStringsI {

    char[] c = null;
    SortedSet<String> set = null;

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        String s = in.nextLine();
        c = s.toCharArray();
        Arrays.sort(c);
        set = new TreeSet<>();
        generate(0);

        out.println(set.size());
        set.forEach(out::println);
    }

    private void generate(int idx) {
        if (idx == c.length - 1) {
            set.add(String.copyValueOf(c));
            return;
        }

        for (int j = idx; j < c.length; j++) {
            swap(idx, j);
            generate(idx+1);
            swap(j, idx);
        }
    }

    private void swap(int i, int j) {
        char temp = c[i];
        c[i] = c[j];
        c[j] = temp;
    }
}
