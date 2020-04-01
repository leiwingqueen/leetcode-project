package com.liyongquan.dp;

import java.util.Stack;

public class Kuohao2 {
    public int longestValidParentheses(String s) {
        Stack<Integer> stack=new Stack<>();
        int maxlength=0;
        stack.push(-1);
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c=='(') {
                stack.push(i);
            }else if (c==')'){
                stack.pop();
                if (stack.isEmpty()) {
                    stack.push(i);
                }
                int length=i-stack.peek();
                maxlength=Math.max(maxlength,length);
            }
        }
        return maxlength;
    }

    public static void main(String[] args) {
        Kuohao2 kuohao2=new Kuohao2();
        int i = kuohao2.longestValidParentheses("()(()");
        System.out.println(i);
    }
}
