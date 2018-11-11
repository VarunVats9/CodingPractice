package com.practice.youtube.rachitjain;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by vvats on 10/11/18.
 */
public class BfsString {


    private static void states(final String target) {

        List<String> list = new ArrayList<>();
        Deque<String> deque = new ArrayDeque<>();
        Map<String, Boolean> visited = new HashMap<>();
        deque.addLast(target);

        while (!deque.isEmpty()) {
            String top = deque.removeFirst();

            if (visited.containsKey(top)) {
                continue;
            }

            visited.put(top, true);

            if (balanced(top)) {
                list.add(top);
            }

            int len = top.length();

            for (int i = 0; i < len; i++) {
                if (top.charAt(i) == '(' || top.charAt(i) == ')') {
                    deque.addLast(top.substring(0, i) + top.substring(i + 1, len));
                }
            }
        }

        System.out.println(list);
    }

    private static boolean balanced(final String top) {
        Deque<Character> stack = new ArrayDeque<>();

        for (int i = 0; i < top.length(); i++) {
            if (top.charAt(i) == '(') {
                stack.addFirst('(');
            }

            if (top.charAt(i) == ')') {
                if (!stack.isEmpty()) {
                    stack.removeFirst();
                } else {
                    return false;
                }
            }
        }

        return stack.isEmpty();
    }


    public static void main(String[] args) {

        {
            BfsString.states("(a)())()");
        }

        {
            BfsString.states("(a()(()a)");
        }

    }
}
