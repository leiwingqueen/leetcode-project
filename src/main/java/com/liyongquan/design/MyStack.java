package com.liyongquan.design;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 使用队列实现栈的下列操作：
 * <p>
 * push(x) -- 元素 x 入栈
 * pop() -- 移除栈顶元素
 * top() -- 获取栈顶元素
 * empty() -- 返回栈是否为空
 * 注意:
 * <p>
 * 你只能使用队列的基本操作-- 也就是 push to back, peek/pop from front, size, 和 is empty 这些操作是合法的。
 * 你所使用的语言也许不支持队列。 你可以使用 list 或者 deque（双端队列）来模拟一个队列 , 只要是标准的队列操作即可。
 * 你可以假设所有操作都是有效的（例如, 对一个空的栈不会调用 pop 或者 top 操作）。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/implement-stack-using-queues
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class MyStack {
    Queue<Integer> in = new LinkedList<>();
    Queue<Integer> out = new LinkedList<>();

    /**
     * Initialize your data structure here.
     */
    public MyStack() {

    }

    /**
     * Push element x onto stack.
     */
    public void push(int x) {
        in.offer(x);
    }

    /**
     * Removes the element on top of the stack and returns that element.
     */
    public int pop() {
        if (in.isEmpty()) {
            return -1;
        }
        while (in.size() > 1) {
            Integer poll = in.poll();
            out.offer(poll);
        }
        //swap
        Queue<Integer> temp = in;
        in = out;
        out = temp;
        return out.poll();
    }

    /**
     * Get the top element.
     */
    public int top() {
        if (in.isEmpty()) {
            return -1;
        }
        int last = -1;
        while (!in.isEmpty()) {
            Integer poll = in.poll();
            out.offer(poll);
            last = poll;
        }
        //swap
        Queue<Integer> temp = in;
        in = out;
        out = temp;
        return last;
    }

    /**
     * Returns whether the stack is empty.
     */
    public boolean empty() {
        return in.isEmpty();
    }
}
