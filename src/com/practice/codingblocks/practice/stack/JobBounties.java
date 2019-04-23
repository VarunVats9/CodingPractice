package com.practice.codingblocks.practice.stack;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class JobBounties {

    private static int max = 0;
    public static void main(String[] args) {

        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in))) {
            String line = bufferedReader.readLine();
            Stack<Integer> stack = new Stack<>();
            stack.add(-1);

            for (int i = 0; i < line.length(); i++) {
                if (line.charAt(i) == '(') {
                    stack.push(i);
                } else {
                    stack.pop();
                    if (stack.empty()) {
                        stack.push(i);
                    } else {
                        max = Math.max(max, i - stack.peek());
                    }
                }
            }

            System.out.println(max);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
