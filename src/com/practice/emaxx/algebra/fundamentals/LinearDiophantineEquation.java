package com.practice.emaxx.algebra.fundamentals;

/**
 * Date : 23 Nov, 2018
 * Time : 6:13 PM
 *
 * @author : Varun Vats (varunvats32@gmail.com)
 */
public class LinearDiophantineEquation {

    private static int lx1, rx1, lx2, rx2;

    /*
     *
     * A linear Diophantine equation equates the sum of two or more monomials,
     * each of degree 1 in one of the variables, to a constant.
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
    private static boolean findAnySolution(final int a, final int b, final int c) {

        int[] coeff = new int[]{1, 1};
        int g = ExtendedGcd.extendGcd(Math.abs(a), Math.abs(b), coeff);

        if (c % g > 0) {
            System.out.println("Solution is NOT possible.");
            return false;
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
        return true;
    }


    /**
     *
     * From the above method, we have already got the answer for one solution.
     *
     * a * x_g * (c/g) + b * y_g * (c/g) = c
     *
     * Let's call these, values as x0 and y0.
     *
     * x0 = x_g * (c/g), and y0 = y_g * (c/g).
     *
     * a * x0 + b * y0 = c, now if we add b/g to x0 and subtract a/g from y0, it won't make any difference.
     *
     * a * (x0 + b/g) + b * (y0 - a/g) = c,
     * it becomes a * x0 + b * y0 + ((a*b)/g - (b*a)/g) = c
     *
     * Same with adding and subtracting 2*b/g and 2*a/g
     *
     * x = x0 + (k*b)/g, y = y0 - (k*a)/g [a and b both are divisible by g]
     */
    private static boolean findAllSolution(final int a, final int b, final int c) {

        int[] coeff = new int[]{1, 1};
        int g = ExtendedGcd.extendGcd(Math.abs(a), Math.abs(b), coeff);

        if (c % g > 0) {
            System.out.println("Solution is NOT possible.");
            return false;
        }

        int x = coeff[0] * (c / g);
        int y = coeff[1] * (c / g);

        if (a < 0) {
            x = -1 * x;
        }

        if (b < 0) {
            y = -1 * y;
        }

        for (int i = 1; i < 2; i++) {
            System.out.println("Solution is possible, with x : " + (x + (i*b)/g) + ", and y : " + (y - (i*a)/g));
        }

        return true;
    }

    /**
     *
     * From the above method, we have reached to this result :
     *
     * x = x0 + (k*b)/g, y = y0 - (k*a)/g [a and b both are divisible by g]
     *
     * Now, suppose x >= xMin and x <= xMax
     *
     * i.e. x0 + (k*b)/g >= xMin ==> k >= (xMin - x0) / (b/g)
     * and for y0 - (k*a)/g >= yMin ==> k >= - (yMin - y0) / (a/g)
     *
     * b = b/g, a = a/g
     *
     * x = x0 + k * b [The k calculated above might be in some fraction rounded to nearest integer]
     * Basically, we need to find the x >= xMin
     */
    private static int findAllSolutionInRange(int a, int b, final int c, final int xMin, final int xMax, final int yMin, final int yMax) {

        if (!findAnySolution(a, b, c)) {
            System.out.println("No solution is found in the range.");
            return 0;
        }

        int[] coeff = new int[]{1, 1};
        int g = ExtendedGcd.extendGcd(Math.abs(a), Math.abs(b), coeff);

        b = b/g; a = a/g;

        int sign_a = a > 0 ? 1 : -1;
        int sign_b = b > 0 ? 1 : -1;

        if (rangeBasedOnXMinXMax(a, b, xMin, xMax, coeff, sign_b)) return 0;
        if (rangeBasedOnYMinXMax(a, b, yMin, yMax, coeff, sign_a)) return 0;

        if (lx2 > rx2) {
            int temp = lx2;
            lx2 = rx2;
            rx2 = temp;
        }

        int lx = Math.max(lx1, lx2);
        int rx = Math.min(rx1, rx2);

        if (lx > rx) {
            return 0;
        }

        /*
         * x = lx + k * b  ==> rx = lx + k * b  ==> (rx - lx) / b = k
         */
        return (rx - lx)/(Math.abs(b)) + 1;
    }

    private static boolean rangeBasedOnYMinXMax(final int a, final int b, final int yMin, final int yMax, final int[] coeff, final int sign_a) {

        shiftSolution(a, b, coeff, -(yMin - coeff[1])/a);

        if (coeff[1] < yMin) {
            shiftSolution(a, b, coeff, sign_a);
        }

        if (coeff[1] > yMax) {
            return true;
        }

        lx2 = coeff[0];

        shiftSolution(a, b, coeff, -(yMax - coeff[1])/a);

        if (coeff[1] > yMax) {
            shiftSolution(a, b, coeff, -sign_a);
        }

        rx2 = coeff[0];

        return false;
    }

    private static boolean rangeBasedOnXMinXMax(final int a, final int b, final int xMin, final int xMax, final int[] coeff, final int sign_b) {

        shiftSolution(a, b, coeff, (xMin - coeff[0])/b);

        if (coeff[0] < xMin) {
            shiftSolution(a, b, coeff, sign_b);
        }

        if (coeff[0] > xMax) {
            return true;
        }

        lx1 = coeff[0];

        shiftSolution(a, b, coeff, (xMax - coeff[0])/b);

        if (coeff[0] > xMax) {
            shiftSolution(a, b, coeff, -sign_b);
        }

        rx1 = coeff[0];

        return false;
    }

    private static void shiftSolution(final int a, final int b, final int[] coeff, final int k) {
        // x and corresponding y value.
        coeff[0] = coeff[0] + k * b;
        coeff[1] = coeff[1] - k * a;
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
            LinearDiophantineEquation.findAllSolution(a, b, c);
        }

        {
            int a = 1, b = 5, c = 9;
            LinearDiophantineEquation.findAllSolution(a, b, c);
        }

        {
            int a = 2, b = 3, c = 9;
            int xMin = -10, xMax = -7, yMin = 8, yMax = 9;
            System.out.println("Total solution possible : " + LinearDiophantineEquation.findAllSolutionInRange(a, b, c, xMin, xMax, yMin, yMax));
        }
    }

}
