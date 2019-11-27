package com.practice.vplanet.course1;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BClassroomWatch {
    public void solve(int testNumber, Scanner in, PrintWriter out) {
        int n = in.nextInt();

        int count = 0;
        List<Integer> l = new ArrayList<>();
        for (int i = n-100; i <= n; i++) {
            if (i > 0 && digitSum(i) == n-i) {
                l.add(i);
                count++;
            }
        }

        out.println(count);

        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < l.size(); i++) {
            sb.append(l.get(i)).append("\n");
        }

        out.println(sb.toString());
    }

    private int digitSum(int num) {
        int sum = 0;
        while (num > 0) {
            sum += num % 10;
            num = num / 10;
        }
        return sum;
    }
}
