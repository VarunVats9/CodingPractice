package com.practice.atcoder.abc154;

import java.util.Scanner;
import java.io.PrintWriter;

public class ARemainingBalls {
    public void solve(int testNumber, Scanner in, PrintWriter out) {
        String l = in.nextLine();
        String[] balls = l.split(" ");
        String n = in.nextLine();
        String[] nums = n.split(" ");
        String u = in.nextLine();

        int s = Integer.parseInt(nums[0]);
        int t = Integer.parseInt(nums[1]);

        if (u.equals(balls[0])) {
            s--;
        } else {
            t--;
        }

        out.println(s + " " + t);
    }
}
