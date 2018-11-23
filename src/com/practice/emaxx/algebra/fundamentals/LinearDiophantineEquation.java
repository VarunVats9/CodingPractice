package com.practice.emaxx.algebra.fundamentals;

/**
 * Date : 23 Nov, 2018
 * Time : 6:13 PM
 *
 * @author : Varun Vats (varunvats32@gmail.com)
 */
public class LinearDiophantineEquation {


    /**
     *
     * Consider the equation : a * x + b * y = c, a solution to this can be found via
     * extended euclidean algorithm.
     *
     * a * x_g + b * y_g = gcd(a, b) = g  --- (1)
     *
     * If you multiply above eq by c and divide both sides by g, it becomes :
     *
     * a * x_g * (c/g) + b * y_g * (c/g) = c --- (2)
     *
     * Now, if you compare this equation with the original one, the values for x, and y are :
     *
     * x = x_g * (c/g), and y = y_g * (c/g).
     *
     * Note : If c is not divisible by g, then there is no solution.
     * Secondly, it works even when either or both a and b are negative,
     * by just changing the sign of x, and y.
     *
     */
    private static void findAnySolution(final int a, final int b, final int c) {

        int[] coeff = new int[]{1, 1};
        int g = ExtendedGcd.extendGcd(Math.abs(a), Math.abs(b), coeff);

        if (c % g > 0) {
            System.out.println("Solution is NOT possible.");
            return;
        }

        int x = coeff[0] * (c / g);
        int y = coeff[1] * (c / g);

        if (a < 0) {
            x = -1 * x;
        }

        if (b < 0) {
            y = -1 * y;
        }

        System.out.println("Solution is possible, with x : " + x + ", and y : " + y);
    }

    public static void main(String[] args) {

        {
            int a = 1, b = 5, c = 9;
            LinearDiophantineEquation.findAnySolution(a, b, c);
        }

        {
            int a = 2, b = 7, c = 15;
            LinearDiophantineEquation.findAnySolution(a, b, c);
        }

        {
            int a = 2, b = 7, c = -15;
            LinearDiophantineEquation.findAnySolution(a, b, c);
        }

        {
            int a = 2, b = 4, c = 9;
            LinearDiophantineEquation.findAnySolution(a, b, c);
        }
    }

}
