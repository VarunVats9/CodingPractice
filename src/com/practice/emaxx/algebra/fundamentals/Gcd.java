package com.practice.emaxx.algebra.fundamentals;

/**
 * Date : 22 Nov, 2018
 * Time : 1:27 PM
 *
 * @author : Varun Vats (varunvats32@gmail.com)
 */
public class Gcd {


    /*
     * Proof of this algorithm is such that :
     *
     * gcd(a, b) = gcd(b, a mod b)
     *
     * If we can prove that gcd(a, b) divides gcd(b, a mod b) and vice versa
     * then, both the left and right sides are equal.
     *
     * Now, a can be written as a = bq + r, which means
     * if d = gcd(a, b) i.e. d divides a (d | a) and d | b.
     * the, d | a => d | bq + r => i.e. if d divides b, it also divides bq
     * which means
     *
     * ==> d | bq and d | r ( r is a mod b ).
     *
     * Now by lemma, if p | q,  p | r then, p | gcd (q, r).
     * Replace p by d (gcd(a, b)), q by b, and r by (a mod b)
     *
     * Hence, gcd(a, b) | gcd(b, a mod b) i.e. left side divides right side. Similarly we can prove that
     * right side divides left side and hence gcd(a, b) == gcd(b, a mod b).
     *
     *
     * If we put p = gcd(b, a mod b), q = a, and r = b, our equation becomes
     * gcd(b, a mod b) | gcd (a, b), and hence we can prove it. But for that we need to show that
     * gcd(b, a mod b) | a and gcd(b, a mod b) | b.
     *
     *
     * Let's assume x = gcd(b, a mod b), which means x | b (proves second part of above) and x | a mod b.
     *
     * x | a mod b = x | a - bq, Now the equation says that x divides a - bq.
     *
     * x * C1 = a - bq, and since x divides b, x * C2 = b
     * => x * C1 = a - x * C2 * q => x (C1 + C2 * q) = a, which means x also divides a.
     *
     * Hence proved. Both sides divide each other, and should be equal.
     *
     */
    private static int gcdRecur(final int a, final int b) {

        if (b == 0) return a;

        return gcdRecur(b, a % b);
    }

    private static int gcd(int a, int b) {

        while (b > 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }

        return a;
    }

    /*
     * lcm (a, b) = (a * b) / ( gcd (a, b) )
     *
     * Complexity of lcm is same as gcd.
     */
    private static int lcm(final int a, final int b) {

        // To avoid integer overflows, first divide and then multiply.
        return (a / gcd(a, b)) * b;
    }


    public static void main(String[] args) {

        {
            System.out.println(Gcd.gcdRecur(5, 3));
        }

        {
            System.out.println(Gcd.gcd(5, 3));
        }

        {
            System.out.println(Gcd.gcdRecur(13, 52));
        }

        {
            System.out.println(Gcd.gcd(13, 52));
        }

        {
            System.out.println(Gcd.gcdRecur(988, 198));
        }

        {
            System.out.println(Gcd.gcd(988, 198));
        }

        {
            System.out.println(Gcd.lcm(12, 76));
        }

        {
            System.out.println(Gcd.lcm(89, 45));
        }
    }

}
