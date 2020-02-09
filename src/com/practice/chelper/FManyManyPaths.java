package com.practice.chelper;

import java.util.Scanner;
import java.io.PrintWriter;

public class FManyManyPaths {
    public void solve(int testNumber, Scanner in, PrintWriter out) {
        int r1 = in.nextInt();
        int c1 = in.nextInt();
        int r2 = in.nextInt();
        int c2 = in.nextInt();

        int r = r2-r1;
        int c = c2-c1;

        Combination comb = new Combination(2000005);
        ModInt ans = comb.calculate(r+c-2, r-1);
        out.println(ans.x);
    }

    public static class Combination {

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

    public static class ModInt {

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
