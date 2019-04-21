package com.practice.codingblocks.practice.heap;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;

import static java.lang.Math.pow;

public class HostelVisit {

    public static void main(String[] args) {

        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in))) {
            String line = bufferedReader.readLine();
            String[] str = line.split(" ");
            int q = Integer.parseInt(str[0]);
            int k = Integer.parseInt(str[1]);

            PriorityQueue<Long> maxHeap = new PriorityQueue<>(Comparator.reverseOrder());
            PriorityQueue<Long> minHeap = new PriorityQueue<>();

            for (int i = 1; i <= q; i++) {
                line = bufferedReader.readLine();
                String[] query = line.split(" ");
                int qt = Integer.parseInt(query[0]);
                if (qt == 1) {
                    int x = Integer.parseInt(query[1]);
                    int y = Integer.parseInt(query[2]);
                    Long dist = (long) (pow(x, 2) + pow(y, 2));
                    if (maxHeap.size() < k) {
                        maxHeap.add(dist);
                    } else {
                        Long maxHeapTop = maxHeap.peek();
                        if (dist <= maxHeapTop) {
                            maxHeap.remove();
                            minHeap.add(maxHeapTop);
                            maxHeap.add(dist);
                        } else {
                            minHeap.add(dist);
                        }
                    }
                } else {
                    System.out.println(maxHeap.peek());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
