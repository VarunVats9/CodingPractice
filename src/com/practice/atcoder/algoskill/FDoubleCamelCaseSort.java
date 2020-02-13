package com.practice.atcoder.algoskill;

import java.util.*;
import java.io.PrintWriter;

public class FDoubleCamelCaseSort {
    public void solve(int testNumber, Scanner in, PrintWriter out) {
        String s = in.nextLine();
        Map<String, String> map = new HashMap<>();
        int flag = 0;
        StringBuilder word = new StringBuilder("");
        List<String> list = new ArrayList<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            word.append(c);
            if (Character.isUpperCase(c)) {
                flag++;
            }
            if (flag % 2 == 0) {
                list.add(word.toString());
                word.setLength(0);
            }
        }


        Collections.sort(list, String.CASE_INSENSITIVE_ORDER);

        StringBuilder sb = new StringBuilder("");
        for (String value : list) {
            sb.append(value);
        }

        out.println(sb.toString());
    }
}
