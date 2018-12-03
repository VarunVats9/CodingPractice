package com.practice.geeksforgeeks.greedy;

/**
 * Date : 02 Dec, 2018
 * Time : 2:30 PM
 *
 * @author : Varun Vats (varunvats32@gmail.com)
 */
public class AnySolutionForCoinsRequired {

    /*
     * This question is similar to DP question, where we need to calculate all the cases, on how
     * to use any change any number of times, and create a currency value.
     *
     * E.g. : Currency Value = 4 and Changes = {1,2,3}
     * Solution : {1,1,1,1}, {1,1,2}, {2,2}, {1,3}
     *
     * But here we need to find a solution wherein instead of dp any quick solution would give result.
     * This gives us the hint that if we use only the maximum amount of change as much as possible.
     * Then, we would need only few changes.
     *
     * Cross Questions :
     * 1. What if by using only the maximum denomination, don't give us any answer.
     * Example : Currency Value = 200 and Changes = {99,4}
     * This way 99 + 99 + 2, we can never get the answer.
     *
     * So, FIRST ASSUMPTION, the answer is possible, only if denomination can create any number.
     * That is the denominations, should be correct.
     *
     * 2. What if the answer, doesn't give minimum changes, but might give actually more changes.
     * Example : Currency Value = 11 and Changes = {9,6,5,1}
     * Our approach gives : 9 + 1 + 1, where as minimum would have been : 6 + 5.
     *
     * So, SECOND ASSUMPTION, the answer only gives any solution, not the minimum changes one.
     *
     * Hence, this greedy approach would give any solution, when the denominations are given correct.
     * Correct in the sense, would be able to make any number, and not just few.
     */

}
