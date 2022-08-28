package com.liyongquan.weeklycontest.wc308;

import java.util.Stack;

public class RemoveStars {
    public String removeStars(String s) {
        Stack<Character> stack = new Stack<>();
        int n = s.length();
        for (int i = 0; i < n; i++) {
            if (s.charAt(i) != '*') {
                stack.add(s.charAt(i));
            } else {
                if (stack.size() > 0) {
                    stack.pop();
                }
            }
        }
        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty()) {
            sb.append((char) stack.pop());
        }
        return sb.reverse().toString();
    }
}
