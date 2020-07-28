package com.practice.egork.lib.concurrency;

import com.practice.egork.lib.io.InputReader;
import com.practice.egork.lib.io.OutputWriter;

/**
 * @author egor@egork.net
 */
public interface Task {
    public void read(InputReader in);

    public void solve();

    public void write(OutputWriter out, int testNumber);
}
