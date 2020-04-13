package com.liyongquan.stack;

import java.util.Stack;

/**
 * 双栈队列
 * https://leetcode-cn.com/problems/yong-liang-ge-zhan-shi-xian-dui-lie-lcof/
 * 两种方法，第一种为一个主队列+一个辅助队列。效率为O(2n)
 * 其实没什么意义，现实世界谁会用效率这么低的方式，纯粹为了出题而出题
 */
public class CQueue {
    private Stack<Integer> s1=new Stack<>();
    private Stack<Integer> s2=new Stack<>();
    public CQueue() {
    }

    public void appendTail(int value) {
        s1.push(value);
    }

    public int deleteHead() {
        if (s1.empty()) {
            return -1;
        }
        while (!s1.empty()){
            s2.push(s1.pop());
        }
        int result=s2.pop();
        while (!s2.empty()){
            s1.push(s2.pop());
        }
        return result;
    }

    public static void main(String[] args) {
        //Your CQueue object will be instantiated and called as such:
        CQueue obj = new CQueue();
        obj.appendTail(5);
        int param_2 = obj.deleteHead();
    }
}
