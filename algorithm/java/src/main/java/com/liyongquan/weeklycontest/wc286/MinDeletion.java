package com.liyongquan.weeklycontest.wc286;

import java.util.Stack;

public class MinDeletion {
    public int minDeletion(int[] nums) {
        Stack<Integer> stack = new Stack<>();
        int cnt = 0;
        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];
            //偶数下标
            if (stack.size() % 2 == 1) {
                if (stack.peek() == num) {
                    stack.pop();
                    cnt++;
                }
            }
            stack.add(num);
        }
        if (stack.size() % 2 == 1) {
            cnt++;
        }
        return cnt;
    }
}
