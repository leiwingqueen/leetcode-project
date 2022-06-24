package com.liyongquan.stack;

import java.util.Stack;

/**
 * 定义栈的数据结构，请在该类型中实现一个能够得到栈的最小元素的 min 函数在该栈中，
 * 调用 min、push 及 pop 的时间复杂度都是 O(1)。
 *
 *
 *
 * 示例:
 *
 * MinStack minStack = new MinStack();
 * minStack.push(-2);
 * minStack.push(0);
 * minStack.push(-3);
 * minStack.min();   --> 返回 -3.
 * minStack.pop();
 * minStack.top();      --> 返回 0.
 * minStack.min();   --> 返回 -2.
 *
 *
 *
 * 提示：
 *
 *     各函数的调用总次数不超过 20000 次
 *
 *
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/bao-han-minhan-shu-de-zhan-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class MinStack {
    /**
     * 其实不容易想到，加一个辅助栈的方式解决
     */
    private Stack<Integer> s1=new Stack<>();
    private Stack<Integer> s2=new Stack<>();
    public MinStack() {
    }

    public void push(int x) {
        s1.push(x);
        if (s2.isEmpty()||s2.peek()>=x) {
            s2.push(x);
        }
    }

    public void pop() {
        if (!s1.isEmpty()) {
            Integer pop = s1.pop();
            if (!s2.isEmpty()&&s2.peek().intValue()==pop.intValue()) {
                s2.pop();
            }
        }
    }

    public int top() {
        return s1.peek();
    }

    public int min() {
        return s2.peek();
    }

    public static void main(String[] args) {
        MinStack stack=new MinStack();
        stack.push(512);
        stack.push(-1024);
        stack.push(-1024);
        stack.push(512);
        System.out.println(stack.min());
        stack.pop();
        System.out.println(stack.min());
        stack.pop();
        System.out.println(stack.min());
        stack.pop();
        System.out.println(stack.min());
    }
}
