package com.practice.atcoder.library;

import java.util.ArrayList;
import java.util.List;

public class Sieve {

    private ArrayList<Integer> primes;
    private int[] f;
    private int n;

    public Sieve(int n) {
        this.n = n;
        primes = new ArrayList<>();
        f = new int[n+1];
        f[0] = f[1] = -1;
        for (int i = 2; i <= n; i++) {
            if (f[i] == 1) continue;
            primes.add(i);
            f[i] = i;
            for (int j = i*i; j <= n; j = j+i) {
                if (f[j] == 0) f[j] = i;
            }
        }
    }

    public boolean isPrime(int x) {
        return f[x] == x;
    }

    public List<Integer> factorList(int x) {
        List<Integer> res = new ArrayList<>();
        while (x != 1) {
            res.add(f[x]);
            x /= f[x];
        }
        return res;
    }

    public List<Pair> factors(int x) {
        List<Integer> fl = factorList(x);
        if (fl.size() == 0) return null;
        List<Pair> res = new ArrayList<>();
        res.add(new Pair(fl.get(0), 0));
        for (int p : fl) {
            if (res.get(res.size() - 1).first == p) {
                res.get(res.size() - 1).second++;
            } else {
                res.add(new Pair(p, 1));
            }
        }
        return res;
    }
}
