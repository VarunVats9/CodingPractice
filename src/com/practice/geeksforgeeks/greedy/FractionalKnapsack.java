package com.practice.geeksforgeeks.greedy;

/**
 * Date : 02 Dec, 2018
 * Time : 2:30 PM
 *
 * @author : Varun Vats (varunvats32@gmail.com)
 */
public class FractionalKnapsack {

    /*
     * The question is different from the usual 0-1 knapsack, where either we
     * can take the item or else not. Here, we can break the item into fractions also.
     *
     * Since, now we can take the items in fraction, the first thing to maximize the
     * knapsack value is to have the items which can give highest, Value/Weight ratio.
     * That is the item which can give highest return if we add that.
     *
     * 1. Hence, we will calculate each item's value/weight ratio, and sort those in non-decreasing
     * order.
     * 2. Once done, we will start and traverse the list and keep on adding the value, if the capacity
     * is greater than item's weight. And with each addition, we will update the remaining capacity.
     * 3. In case, the capacity < weight of the item, we will divide that into fraction, and we break our loop
     * here.
     *
     * Edge Cases :
     * 1. What if the first item's weight > capacity ?
     * Since, first item gives most return, will add only that item and that too in fraction.
     *
     * 2. Can it be possible that after fraction, we will not break our loop.
     * No, that cannot be possible, since, that is the same sub-problem as first.
     */
}
