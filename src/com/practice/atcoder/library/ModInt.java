package com.practice.atcoder.library;

public class ModInt {

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
        if ((c.x += mod - b.x) >= mod) c.x -= mod;
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
        if ((x += mod - a.x) >= mod) x -= mod;
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
