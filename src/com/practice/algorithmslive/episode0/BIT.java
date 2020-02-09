package com.practice.algorithmslive.episode0;

public class BIT {

    private int size;
    private int[] table;

    public BIT(int size) {
        this.size = size;
        table = new int[size];
    }

    public int sum(int i) {
        int sum = 0;
        while (i > 0) {
            sum += table[i];
            i -= Integer.lowestOneBit(i);
        }
        return sum;
    }

    public void update(int i, int delta) {
        while (i < size) {
            table[i] += delta;
            i += Integer.lowestOneBit(i);
        }
    }

    public int rangeSum(int i, int j) {
        return sum(j) - sum(i-1);
    }
}
