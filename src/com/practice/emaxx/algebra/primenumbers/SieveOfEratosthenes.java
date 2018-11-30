package com.practice.emaxx.algebra.primenumbers;

import static java.util.Arrays.fill;

import java.util.ArrayList;
import java.util.List;

/**
 * Date : 25 Nov, 2018
 * Time : 1:59 PM
 *
 * @author : Varun Vats (varunvats32@gmail.com)
 */
public class SieveOfEratosthenes {

    // Block size, should be set accordingly, based on your inputs.
    private static final int BLOCK_SIZE = 10;

    private static final List<Integer> primes = new ArrayList<>();

    private static void sieve(final int n) {

        boolean[] notPrime = new boolean[n+1];

        /*
         * 1 is not a prime number, a prime number is a positive number divisible by itself and 1, that is
         * it should have exactly two positive divisors.
         */
        notPrime[0] = notPrime[1] = true;

        /*
         * Why only till i * i <= n ?
         *
         * Think of this as a * b = n, because to achieve the maximum of both at same time, a and b
         * so that value remains n, is when both are equal that is a == b.
         *
         * For example, suppose n = 36, then all the values of a * b are :
         *
         * 1 * 36, 2 * 18, 3 * 12, 4 * 9, 6 * 6, (next values are all covered, just switch a and b) 9 * 4, 12 * 3, 36 * 1
         * As, you can see the maximum value of both at the same time, is 6 * 6.
         *
         */
        for (int i = 2; i * i <= n; i++) {
            if (!notPrime[i]) {

                /*
                 * Now, why we have started from j = i*i ?
                 *
                 * Because the numbers below this, have already been covered by the prime numbers, prior to it.
                 *
                 * Suppose, i = 7, j will start from j = 49.
                 *
                 * 7 * 7 = 49
                 * So, the numbers from 1 to 48 must have already been covered. Example :
                 *
                 * 7 * 1 = Prime 1 table
                 * 7 * 2 = Prime 2 table
                 * 7 * 3 = Prime 3 table
                 * 7 * 4 (14 * 2) = Prime 2 table
                 * 7 * 5 = Prime 5 table
                 * 7 * 6 (21 * 3 or 14 * 3) = Prime 2 table
                 *
                 * Hence, we did not start like k = 1 ... i-1, and directly start from i * i.
                 */
                for (int j = i*i; j <= n; j = j + i) {
                    notPrime[j] = true;
                }
            }
        }

        primes.clear();

        for (int i = 1; i <= n ; i++) {
            if (!notPrime[i]) {
                primes.add(i);
            }
        }

        System.out.print("Total primes from 1 to " + n + " : " + primes.size() + ", ");
        System.out.println("Prime numbers : " + primes);
    }


    /**
     * Segmented sieve or block sieve, is based on the following concept:
     *
     * Suppose, you have to find primes between L and R range, and
     * if you already have primes from (1 to sqrt(R)), then you can go through all
     * the multiples of prime in (1 to sqrt(R)), over the range L to R.
     *
     * But why so why not directly create a array from 1 to R and calculate the answer through sieve ?
     *
     * Then what if the R is Integer.MAX_VALUE, in that case you cannot create an array of such a large size.
     * whereas L to R is in the range 10^6 which we can create as an array.
     *
     *
     * Now, what if the L to R range is also big and we cannot create an array of such a big size, then what to do,
     * in tht case why not divide the range into k blocks, wherein k size array can be formed.
     *
     * Now, the question is how to get the primes from (1 to sqrt(R)).
     * Simple via normal sieve method.
     *
     */
    private static void blockSieve(final int n) {

        primes.clear();
        int sqrtN = (int)Math.sqrt(n);
        sieve(sqrtN);

        int countPrimes = 0;

        boolean[] block = new boolean[BLOCK_SIZE];

        for (int k = 0; k * BLOCK_SIZE <= n; k++) {

            fill(block, true);
            int startBlock = k * BLOCK_SIZE;

            for (int i = 0; i < primes.size(); i++) {
                int p = primes.get(i);

                /*
                 * First, let's see how to define the ceiling function.
                 *
                 * Ceiling function gives the answer rounding to the nearest integer, greater than the current.
                 *
                 * Example : 3.0002 ~ 4, 4.9999 ~ 5, but 5.0000 ~ 5.
                 *
                 * So, let's see how a/b can be changed to ceiling function (a+b-1)/b.
                 *
                 * Now, it is nothing but => (a/b) + 1 - (1/b)
                 * So, either 'a' is a multiple of 'b' or not.
                 *
                 * Let's say it is multiple of b, and a/b = c, then => c + 1 - (0.jkl), where c is an integer.
                 * which would mean, it becomes c + 0.xyz = c.xyz, but it gets casted to integer hence ans is c.
                 *
                 * Now, suppose 'a' is not a multiple of 'b', then if a = cb + r answer should be c+1.
                 * Now, answer is => c + r/b + 1 - 1/b => (c + 1 + (r-1)/b), and r is between 1 to b-1.
                 *
                 * Hence, answer => (c+1) + (0.xyz) => (c+1).xyz => integer cast c+1.
                 */
                int ceilingQuotient = (startBlock + p - 1) / p;

                /*
                 * So, basically ceilingQuotient, gives the first multiple of the p, >= startBlock.
                 * And now, (ceilingQuotient * p) - startBlock, gives the first index in the block.
                 *
                 * From here, need to set false for all the index + i * p, values.
                 *
                 * As mentioned, in the sieve method also, the numbers below i * i, should have already been
                 * marked false, by prior primes. Hence, make sure the values start from max(ceilingQuotient, p) * p.
                 */
                int j = Math.max(ceilingQuotient, p) * p - startBlock;
                for (; j < BLOCK_SIZE; j = j + p) {
                    block[j] = false;
                }
            }

            if (k == 0) {
                block[0] = block[1] = false;
            }

            for (int l = 0; l < BLOCK_SIZE && (l + startBlock <= n); l++) {
                if (block[l]) {
                    countPrimes++;
                }
            }
        }

        System.out.println("Total primes from 1 to " + n + " : " + countPrimes);
    }

    public static void main(String[] args) {

        {
            SieveOfEratosthenes.sieve(129);
        }

        {
            SieveOfEratosthenes.sieve(31);
        }

        {
            SieveOfEratosthenes.blockSieve(129);
        }

        {
            SieveOfEratosthenes.blockSieve(31);
        }
    }

}
