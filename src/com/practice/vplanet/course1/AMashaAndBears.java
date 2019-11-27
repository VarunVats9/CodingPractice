package com.practice.vplanet.course1;

import java.io.PrintWriter;
import java.util.Scanner;

public class AMashaAndBears {
    public void solve(int testNumber, Scanner in, PrintWriter out) {
        int V1 = in.nextInt();
        int V2 = in.nextInt();
        int V3 = in.nextInt();
        int Vm = in.nextInt();

        if (Vm >= V1 || Vm >= V2) {
            out.println(-1);
            return;
        }

        if (Math.max(Vm, V3) > 2 * Math.min(V3, Vm)) {
            out.println(-1);
            return;
        }

        out.println(2*V1);
        out.println(2*V2);
        out.println(2*Math.min(V3, Vm));

    }
}
