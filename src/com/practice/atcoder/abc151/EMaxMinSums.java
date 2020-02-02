package com.practice.atcoder.abc151;

import java.util.Arrays;
import java.util.Scanner;
import java.io.PrintWriter;

public class EMaxMinSums {
    public void solve(int testNumber, Scanner in, PrintWriter out) {
        int n = in.nextInt();
        int k = in.nextInt();

        Integer[] arr = new Integer[n];

        for (int i = 0; i < arr.length; i++) {
            arr[i] = in.nextInt();
        }

        Arrays.sort(arr);

        Combination comb = new Combination(n);
        ModInt ans = new ModInt(0);

        for (int j = 0; j < n; j++) {
            ModInt c1 = comb.calculate(j, k-1);
            ModInt c2 = comb.calculate(n-j-1, k-1);
            ans = ans.add(ModInt.multiply(new ModInt(arr[j]), ModInt.add(c1, c2.negative())));
        }

        out.println(ans.x);
    }

    private static class Combination {

        private static final int mod = 1000000007;

        public ModInt[] fact, ifact;

        public Combination(int n) {
            fact = new ModInt[n+1];
            ifact = new ModInt[n+1];

            assert(n < mod);
            fact[0] = new ModInt(1);
            for (int i = 1; i <= n; i++) {
                fact[i] = ModInt.multiply(fact[i-1], new ModInt(i));
            }

            ifact[n] = fact[n].inv();
            for (int i = n; i >= 1; i--) {
                ifact[i-1] = ModInt.multiply(ifact[i], new ModInt(i));
            }
        }

        public ModInt calculate(int n, int k) {
            if (k < 0 || k > n) return new ModInt(0);
            return ModInt.multiply(fact[n], ModInt.multiply(ifact[k], ifact[n-k]));
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
}


