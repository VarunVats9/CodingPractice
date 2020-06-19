package com.practice.cses.introductoryproblems;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

/**
 * Built using CHelper plug-in
 * Actual solution is at the top
 *
 * @author varunvats32
 */
public class Apartments {
    public static void main(String[] args) throws IOException {
        //long start = System.currentTimeMillis();
        //BufferedReader inputStream = Files.newBufferedReader(Paths.get("/home/vvats/Downloads/test_input.txt"));
        //BufferedReader inputStream = new BufferedReader(new InputStreamReader(System.in));
        OutputStream outputStream = System.out;
        Reader reader = new Reader();
        //Reader reader = new Reader("/home/vvats/Downloads/test_input.txt");
        PrintWriter out = new PrintWriter(outputStream);
        Solver solver = new Solver();
        solver.solve(1, reader, out);
        //out.println((System.currentTimeMillis() - start)/1000);
        out.close();
    }

    static class Solver {
        public void solve(int testNumber, Reader in, PrintWriter out) throws IOException {
            int n = in.nextInt(), m = in.nextInt();
            int k = in.nextInt();
            ArrayList<Integer> a = new ArrayList<>();
            int b[] = new int[m];

            for (int i = 0; i < n; i++) {
                a.add(in.nextInt());
            }
            for (int j = 0; j < m; j++) {
                b[j] = in.nextInt();
            }

            Collections.sort(a);
            Arrays.sort(b);

            int i = 0, j = 0, count = 0;
            int low = Math.max(a.get(i) - k, 0);
            int high = a.get(i) + k;
            while (j < m) {
                if (b[j] >= low && b[j] <= high) {
                    count++;
                    i++;
                    j++;
                    if (i >= n) break;
                    low = Math.max(a.get(i) - k, 0);
                    high = a.get(i) + k;
                } else if (b[j] < low) {
                    j++;
                } else {
                    i++;
                    if (i >= n) break;
                    low = Math.max(a.get(i) - k, 0);
                    high = a.get(i) + k;
                }
            }

            out.println(count);
        }

    }

    static class Reader
    {
        final private int BUFFER_SIZE = 1 << 16;
        private DataInputStream din;
        private byte[] buffer;
        private int bufferPointer, bytesRead;

        public Reader()
        {
            din = new DataInputStream(System.in);
            buffer = new byte[BUFFER_SIZE];
            bufferPointer = bytesRead = 0;
        }

        public Reader(String file_name) throws IOException
        {
            din = new DataInputStream(new FileInputStream(file_name));
            buffer = new byte[BUFFER_SIZE];
            bufferPointer = bytesRead = 0;
        }

        public String readLine() throws IOException
        {
            byte[] buf = new byte[64]; // line length
            int cnt = 0, c;
            while ((c = read()) != -1)
            {
                if (c == '\n')
                    break;
                buf[cnt++] = (byte) c;
            }
            return new String(buf, 0, cnt);
        }

        public int nextInt() throws IOException
        {
            int ret = 0;
            byte c = read();
            while (c <= ' ')
                c = read();
            boolean neg = (c == '-');
            if (neg)
                c = read();
            do
            {
                ret = ret * 10 + c - '0';
            }  while ((c = read()) >= '0' && c <= '9');

            if (neg)
                return -ret;
            return ret;
        }

        public long nextLong() throws IOException
        {
            long ret = 0;
            byte c = read();
            while (c <= ' ')
                c = read();
            boolean neg = (c == '-');
            if (neg)
                c = read();
            do {
                ret = ret * 10 + c - '0';
            }
            while ((c = read()) >= '0' && c <= '9');
            if (neg)
                return -ret;
            return ret;
        }

        public double nextDouble() throws IOException
        {
            double ret = 0, div = 1;
            byte c = read();
            while (c <= ' ')
                c = read();
            boolean neg = (c == '-');
            if (neg)
                c = read();

            do {
                ret = ret * 10 + c - '0';
            }
            while ((c = read()) >= '0' && c <= '9');

            if (c == '.')
            {
                while ((c = read()) >= '0' && c <= '9')
                {
                    ret += (c - '0') / (div *= 10);
                }
            }

            if (neg)
                return -ret;
            return ret;
        }

        private void fillBuffer() throws IOException
        {
            bytesRead = din.read(buffer, bufferPointer = 0, BUFFER_SIZE);
            if (bytesRead == -1)
                buffer[0] = -1;
        }

        private byte read() throws IOException
        {
            if (bufferPointer == bytesRead)
                fillBuffer();
            return buffer[bufferPointer++];
        }

        public void close() throws IOException
        {
            if (din == null)
                return;
            din.close();
        }
    }
}

