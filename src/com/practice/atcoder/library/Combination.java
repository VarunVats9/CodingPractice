package com.practice.atcoder.library;

public class Combination {

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
