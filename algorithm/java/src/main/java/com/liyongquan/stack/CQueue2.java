package com.liyongquan.stack;

import java.util.Stack;

/**
 * 双栈队列第二种解法。in队列+out队列
 * https://leetcode-cn.com/problems/yong-liang-ge-zhan-shi-xian-dui-lie-lcof/
 * 比第一种方法稍好，pop的最差复杂度O(n)，此好为O(1)
 */
public class CQueue2 {
    private Stack<Integer> in=new Stack<>();
    private Stack<Integer> out=new Stack<>();
    public CQueue2() {
    }

    public void appendTail(int value) {
        in.push(value);
    }

    public int deleteHead() {
        if (out.isEmpty()) {
            while (!in.empty()){
                out.push(in.pop());
            }
        }
        if (out.isEmpty()) {
            return -1;
        }
        return out.pop();
    }
}
