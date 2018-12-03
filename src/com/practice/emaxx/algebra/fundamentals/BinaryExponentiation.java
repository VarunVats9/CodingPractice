package com.practice.emaxx.algebra.fundamentals;

/**
 * Date : 22 Nov, 2018
 * Time : 10:37 AM
 *
 * @author : Varun Vats (varunvats32@gmail.com)
 */
public class BinaryExponentiation {

    final static int MOD = 1_000_000_009;

    private static long binaryPowRecur(long a, long b) {

        if (b == 0) return 1;

        long ans = binaryPowRecur(a, b/2);

        if (b % 2 == 0) {
            return ans * ans;
        }

        return a * ans * ans;
    }


    /*
     * This is not very intuitive approach, but think this way :
     *
     * It computes all the powers in a loop, and multiplies the ones with the
     * corresponding set bit in n.
     *
     * Suppose the number is pow (a, b(13)). Since 13 can be written as 1101 in binary,
     * which is 8 + 4 + 0 + 1 = 13. That is if you know all the powers of 2, you just need to multiply.
     * a^8 * a^4 * a^1 = a^13.
     *
     * Hence complexity is O(logn) because the powers n can be written in log n digits, and you
     * just need to multiply those having bit as 1.
     *
     * So, to get the all the set bits, we can do this by shifting the number by 1 bit each time and check if the
     * least bit is one or not.
     *
     * Like 13 = 1101, shift by one = 110, shift = 11, shift = 1, shift = 0.
     *
     */
    private static long binaryPow(long a, long b) {

        long res = 1;

        while (b > 0) {

            // check least significant bit.
            if ((b & 1) > 0) {

                /*
                 * This 'a' here, is actually not the 'a' mentioned in the parameter.
                 * Here, it represents, the pow of 'a' formed by multiplying a * a, each time
                 * in line 63. Here, it can be a^1 or a^2 or a^4 ..... a^2*n and so on.
                 */
                res = res * a;
            }

            a = a * a;

            // Shift by one.
            b = b >> 1;
        }

        return res;
    }

    private static long binaryPowMod(long a, long b, long mod) {

        a = a % mod;

        long res = 1;

        while (b > 0) {

            // check least significant bit.
            if ((b & 1) > 0) {
                res = (res * a) % mod;
            }

            a = (a * a) % mod;

            // Shift by one.
            b = b >> 1;
        }

        return res;
    }


    public static void main(String[] args) {

        {
            System.out.println(BinaryExponentiation.binaryPowRecur(21231, 1234));
        }

        {
            System.out.println(BinaryExponentiation.binaryPow(21231, 1234));
        }

        {
            System.out.println(BinaryExponentiation.binaryPowRecur(22346, 41));
        }

        {
            System.out.println(BinaryExponentiation.binaryPow(22346, 41));
        }

        {
            System.out.println(BinaryExponentiation.binaryPowRecur(2890, 5));
        }

        {
            System.out.println(BinaryExponentiation.binaryPow(2890, 5));
        }

        {
            System.out.println(BinaryExponentiation.binaryPowMod(2890, 53411134, MOD));
        }

        {
            System.out.println(BinaryExponentiation.binaryPowMod(289124410, 971726478, MOD));
        }

    }

}
