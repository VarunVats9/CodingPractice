package com.practice.emaxx.algebra.fundamentals;

import com.practice.emaxx.geometry.Pair;

/**
 * Date : 22 Nov, 2018
 * Time : 10:39 PM
 *
 * @author : Varun Vats (varunvats32@gmail.com)
 */
public class FibonnaciNumbers {

    /*
     * Two important equations : These are derived from matrix multiplication
     * while calculating fibonacci numbers.
     *
     * Even, F(2k) = F(k) * (2 * F(k+1) - F(k))
     * Odd, F(2k+1) = F(k+1) ^ 2 + F(k) ^ 2
     *
     * It is based on the fact that, if you know F(k) and F(k+1)
     * you can calculate F(2k) and F(2k+1) with these equations.
     * But remember, if you have F(2k) + F(2k+1) = F(2K+2).
     *
     * So, example : k = 0, then if you know F(0) and F(1), you can calculate
     * F(2*0), F(2*0+1) and F(2*0+2), basically F(0), F(1) and F(2).
     *
     * Same if you know, k = 1, F(1) and F(2) then you can calculate F(2) and F(3).
     *
     * Example : with (0, 1) --> (1, 2) --> (2, 3) --> (4, 5) --> (8, 9) --> (16, 17)
     */
    private static Pair<Integer, Integer> fib(final long n) {

        if (n == 0) {
            return new Pair<>(0, 1);
        }

        Pair<Integer, Integer> pair = fib(n >> 1);

        /*
         * Now, suppose 2k+1 (which is also n) = 17, 2k = 16.
         * Now, these two can be derived from (8 (pair.left), 9 (pair.right)), and so on.
         *
         * Once all the recursion has been done, function should return n, and n+1.
         *
         * Here, n is odd, that is, it should return 2k+1, and 2K+2 (2k sum 2k+1).
         *
         * Had it been n = 16, ie. even, it should have just return the 2k, and 2k+1.
         */
        int even = pair.getLeft() * (2 * pair.getRight() - pair.getLeft());
        int odd = pair.getRight() * pair.getRight() + pair.getLeft() * pair.getLeft();

        if ((n & 1) > 0) {
            return new Pair<>(odd, (odd + even));
        }

        return new Pair<>(even, odd);
    }

    public static void main(String[] args) {

        {
            System.out.println(FibonnaciNumbers.fib(17L));
        }

        {
            System.out.println(FibonnaciNumbers.fib(16L));
        }

        {
            System.out.println(FibonnaciNumbers.fib(5L));
        }

        {
            System.out.println(FibonnaciNumbers.fib(1L));
        }

        {
            System.out.println(FibonnaciNumbers.fib(0L));
        }
    }

}
