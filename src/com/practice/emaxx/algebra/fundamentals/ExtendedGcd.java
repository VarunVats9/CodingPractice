package com.practice.emaxx.algebra.fundamentals;

/**
 * Date : 22 Nov, 2018
 * Time : 5:45 PM
 *
 * @author : Varun Vats (varunvats32@gmail.com)
 */
public class ExtendedGcd {

    /*
     * This algorithm, computes the gcd in the form of a, b itself.
     *
     * ax + by = gcd(a, b) = g ---- (1)
     * bx + (a mod b)y = gcd(b, a mod b) = g
     *
     * Now, since both the equations are equal and are g.
     *
     * Let's rearrange the second equation, and considering the values found as x1, and y1:
     * bx1 + (a - b*floor(a/b))y1 = g
     * b(x1-b*floor(a/b)y1) + a(y1) = g ---- (2)
     *
     * Comparing eq, (1) and (2)
     *
     * x = y1 and y = (x1 - b*floor(a/b)*y1)  ----- (3)
     *
     * That is going from gcd(a, b) to gcd(b, a mod b), the x and y changes according to the
     * above equations.
     *
     * Let's take an example : gcd(14, 3)
     * gcd(14, 3) = 14(x) + 3(y)
     * gcd(3, 2) = 3(x1) + 2(y1)
     * gcd(2, 1) = 2(x2) + 1(y2)
     * gcd(1, 0) = 1(x3) + 0(y3)
     *
     * So, if you use equation (3), we can get x2, y2 from x3, y3 by applying equation (3).
     * Similarly, we can get x1, y1 from x2, y2 by applying equation (3).
     * And, we can get x, y from x1, y1 by applying equation(3).
     */
    public static int extendGcd(final int a, final int b, final int[] coeff) {
        if (b == 0) {
            coeff[0] = 1; coeff[1] = 0;
            return a;
        }

        int[] t = new int[]{1, 1};
        int g = extendGcd(b, a%b, t);
        coeff[0] = t[1];
        coeff[1] = t[0] - (a/b) * t[1];

        return g;
    }

    public static void main(String[] args) {

        {
            int[] coeff = new int[]{1, 1};
            int a = 35, b = 15;
            int gcd = ExtendedGcd.extendGcd(a, b, coeff);
            System.out.println("The x and y variables are : " + coeff[0] + " * " + a + " + " + coeff[1] + " * " + b + " = " + gcd);
        }

        {
            int[] coeff = new int[]{1, 1};
            int a = 678, b = 342;
            int gcd = ExtendedGcd.extendGcd(a, b, coeff);
            System.out.println("The x and y variables are : " + coeff[0] + " * " + a + " + " + coeff[1] + " * " + b + " = " + gcd);
        }
    }
}
