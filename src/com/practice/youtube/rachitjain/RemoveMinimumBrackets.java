package com.practice.youtube.rachitjain;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.practice.emaxx.geometry.Pair;

/**
 * Remove minimum brackets to get balanced bracket sequence, and print all such sequences.
 */
public class RemoveMinimumBrackets {

    private static void states(final String target) {

        // Pair of the isBalanced string and the distance from the target. (target is source in BFS.)
        List<Pair<String, Integer>> list = new ArrayList<>();

        // Act as a queue in BFS traversal.
        Deque<Pair<String, Integer>> deque = new ArrayDeque<>();

        // Keeping a map of already visited isBalanced/unbalanced string
        Map<String, Boolean> visited = new HashMap<>();

        deque.addLast(new Pair<>(target, 0));

        while (!deque.isEmpty()) {
            Pair<String, Integer> pair = deque.removeFirst();
            String top = pair.getLeft();

            // Skip if already been visited.
            if (visited.containsKey(top)) {
                continue;
            }

            visited.put(top, true);

            /*
             * If the string is balanced, add that to the list.
             */
            if (isBalanced(top)) {
                list.add(pair);
                continue;
            }

            int len = top.length();

            for (int i = 0; i < len; i++) {
                if (top.charAt(i) == '(' || top.charAt(i) == ')') {
                    deque.addLast(new Pair<>(top.substring(0, i) + top.substring(i + 1, len), pair.getRight() + 1));
                }
            }
        }

        System.out.println(list);
    }

    private static boolean isBalanced(final String top) {

        int count = 0;

        for (int i = 0; i < top.length(); i++) {

            if (top.charAt(i) == '(') {
                count++;
            } else if (top.charAt(i) == ')') {
                count--;
            }

            if (count < 0) return false;
        }

        return count == 0;
    }

    public static void main(String[] args) {

        {
            RemoveMinimumBrackets.states("(a)())()");
        }

        {
            RemoveMinimumBrackets.states("(a()(()a)");
        }

        {
            RemoveMinimumBrackets.states("()(()))");
        }

        {
            RemoveMinimumBrackets.states("()()()()()()()");
        }

    }
}
