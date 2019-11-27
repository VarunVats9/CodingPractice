package com.practice.vplanet.course1;

import java.io.PrintWriter;
import java.util.Scanner;

public class AQAQ {
    public void solve(int testNumber, Scanner in, PrintWriter out) {
        String s = in.nextLine();
        int n = s.length();

        int count = 0;
        for (int i = 0; i < n-2; i++) {
            for (int j = i+1; j < n-1; j++) {
                for (int k = j+1; k < n; k++) {
                    if (s.charAt(i) == 'Q' && s.charAt(j) == 'A'
                            && s.charAt(k) == 'Q') {
                        count++;
                    }
                }
            }
        }

        out.println(count);
    }
}
