package com.practice.atcoder.abc152;

import java.util.*;
import java.io.PrintWriter;

public class EFlatten {
    public void solve(int testNumber, Scanner in, PrintWriter out) {
        int n = in.nextInt();
        int[] arr = new int[n];

        for (int i = 0; i < n; i++) {
            arr[i] = in.nextInt();
        }

        Sieve sieve = new Sieve(1000000);
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            List<Pair> f = sieve.factors(arr[i]);
            for (Pair p : f) {
                Integer pow = map.putIfAbsent(p.first, p.second);
                if (pow != null) {
                    map.put(p.first, Math.max(p.second, map.get(p.first)));
                }
            }
        }

        ModInt lcm = new ModInt(1);
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            for (int i = 0; i < entry.getValue(); i++) {
                lcm.multiply(new ModInt(entry.getKey()));
            }
        }

        ModInt ans = new ModInt(0);
        for (int i = 0; i < arr.length; i++) {
            ModInt res = ModInt.divide(lcm, new ModInt(arr[i]));
            ans.add(res);
        }

        out.println(ans.x);
    }

    public static class Sieve {

        private ArrayList<Integer> primes;
        private int[] f;
        private int n;

        public Sieve(int n) {
            this.n = n;
            primes = new ArrayList<>();
            f = new int[n+1];
            f[0] = f[1] = -1;
            for (int i = 2; i <= n; i++) {
                if (f[i] > 0) continue;
                primes.add(i);
                f[i] = i;
                for (long j = (long)i*i; j <= n; j = j+i) {
                    if (f[(int)j] == 0) f[(int)j] = i;
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
            if (fl.size() == 0) return new ArrayList<>();
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

    private static class ModInt {

        private static final int mod = 1000000007;

        public long x;

        public ModInt(long x) {
            this.x = (x % mod + mod) % mod;
        }

        public ModInt negative() {
            return new ModInt(-x);
        }

        public static ModInt add(ModInt a, ModInt b) {
            ModInt c = new ModInt(a.x);
            if ((c.x += b.x) >= mod) c.x -= mod;
            return c;
        }

        public static ModInt subtract(ModInt a, ModInt b) {
            ModInt c = new ModInt(a.x);
            if ((c.x -= b.x) >= mod) c.x -= mod;
            return c;
        }

        public static ModInt multiply(ModInt a, ModInt b) {
            ModInt c = new ModInt(a.x);
            c.x *= b.x;
            c.x %= mod;
            return c;
        }

        public static ModInt divide(ModInt a, ModInt b) {
            ModInt c = new ModInt(a.x);
            c.divide(b);
            return c;
        }

        public ModInt add(ModInt a) {
            if ((x += a.x) >= mod) x -= mod;
            return new ModInt(x);
        }

        public ModInt subtract(ModInt a) {
            if ((x -= a.x) >= mod) x -= mod;
            return new ModInt(x);
        }

        public ModInt multiply(ModInt a) {
            x *= a.x;
            x %= mod;
            return new ModInt(x);
        }

        public ModInt inv() {
            return pow(mod-2);
        }

        public ModInt divide(ModInt a) {
            return this.multiply(a.inv());
        }

        public ModInt pow(long p) {
            if (p == 0) return new ModInt(1);
            ModInt r = pow(p >> 1);
            r = r.multiply(r);
            if ((p&1) == 1)
                r = r.multiply(this);
            return r;
        }
    }

    private static class Pair {

        public int first, second;

        public Pair(int first, int second) {
            this.first = first;
            this.second = second;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Pair pair = (Pair) o;
            return first == pair.first &&
                    second == pair.second;
        }

        @Override
        public int hashCode() {
            return Objects.hash(first, second);
        }
    }

}
