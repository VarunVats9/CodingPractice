package com.practice.codingblocks.practice.numbertheory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;

public class BigGcd {

    private static BigInteger gcd(BigInteger n, BigInteger m) {
        if (m.equals(new BigInteger("0"))) {
            return n;
        }
        return gcd(m, n.mod(m));
    }

    public static void main(String[] args) {

        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in))) {
            String line = bufferedReader.readLine();
            String[] str = line.split(" ");
            BigInteger n = new BigInteger(str[0]);
            BigInteger m = new BigInteger(str[1]);
            System.out.println(gcd(n, m));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
