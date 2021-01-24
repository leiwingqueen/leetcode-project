package com.liyongquan.stack;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Stack;

/**
 * 设计一个最大栈数据结构，既支持栈操作，又支持查找栈中最大元素。
 * <p>
 * 实现 MaxStack 类：
 * <p>
 * MaxStack() 初始化栈对象
 * void push(int x) 将元素 x 压入栈中。
 * int pop() 移除栈顶元素并返回这个元素。
 * int top() 返回栈顶元素，无需移除。
 * int peekMax() 检索并返回栈中最大元素，无需移除。
 * int popMax() 检索并返回栈中最大元素，并将其移除。如果有多个最大元素，只要移除 最靠近栈顶 的那个。
 *  
 * <p>
 * 示例：
 * <p>
 * 输入
 * ["MaxStack", "push", "push", "push", "top", "popMax", "top", "peekMax", "pop", "top"]
 * [[], [5], [1], [5], [], [], [], [], [], []]
 * 输出
 * [null, null, null, null, 5, 5, 1, 5, 1, 5]
 * <p>
 * 解释
 * MaxStack stk = new MaxStack();
 * stk.push(5);   // [5] - 5 既是栈顶元素，也是最大元素
 * stk.push(1);   // [5, 1] - 栈顶元素是 1，最大元素是 5
 * stk.push(5);   // [5, 1, 5] - 5 既是栈顶元素，也是最大元素
 * stk.top();     // 返回 5，[5, 1, 5] - 栈没有改变
 * stk.popMax();  // 返回 5，[5, 1] - 栈发生改变，栈顶元素不再是最大元素
 * stk.top();     // 返回 1，[5, 1] - 栈没有改变
 * stk.peekMax(); // 返回 5，[5, 1] - 栈没有改变
 * stk.pop();     // 返回 1，[5] - 此操作后，5 既是栈顶元素，也是最大元素
 * stk.top();     // 返回 5，[5] - 栈没有改变
 *  
 * <p>
 * 提示：
 * <p>
 * -107 <= x <= 107
 * 最多调用 104 次 push、pop、top、peekMax 和 popMax
 * 调用 pop、top、peekMax 或 popMax 时，栈中 至少存在一个元素
 *  
 * <p>
 * 进阶： 
 * <p>
 * 试着设计解决方案：调用 top 方法的时间复杂度为 O(1) ，调用其他方法的时间复杂度为 O(logn) 。 
 * <p>
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/max-stack
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class MaxStack {
    /**
     * main stack
     */
    private Deque<Integer> mStack;
    /**
     * 辅助栈，主要是协助popMax的时候使用
     */
    private Deque<Integer> tmpStack;
    /**
     * 最大栈，单调递增，维护当前的最大值
     */
    private Deque<Integer> maxStack;

    public MaxStack() {
        this.mStack = new LinkedList<>();
        this.tmpStack = new LinkedList<>();
        this.maxStack = new LinkedList<>();
    }

    public void push(int x) {
        mStack.offerFirst(x);
        //维护最大栈
        if (maxStack.size() == 0 || maxStack.peekFirst() <= x) {
            maxStack.offerFirst(x);
        }
    }

    public int pop() {
        if (mStack.size() == 0) {
            return -1;
        }
        //维护最大栈
        Integer poll = mStack.pollFirst();
        if (maxStack.peekFirst().intValue() == poll.intValue()) {
            maxStack.pollFirst();
        }
        return poll;
    }

    public int top() {
        return mStack.size() == 0 ? -1 : mStack.peekFirst();
    }

    public int peekMax() {
        return maxStack.size() == 0 ? -1 : maxStack.peekFirst();
    }

    /**
     * 时间复杂度O(log(n))?
     *
     * @return
     */
    public int popMax() {
        if (mStack.size() == 0) {
            return -1;
        }
        Integer max = maxStack.pollFirst();
        //依次把max以上的元素移动到tmpStack
        while (max.intValue() != mStack.peekFirst()) {
            tmpStack.push(mStack.pollFirst());
        }
        //移除max
        mStack.pollFirst();
        //然后再把tmpStack的移入栈中，注意maxStack的也要更新，继续维护单调递增的特性
        while (!tmpStack.isEmpty()) {
            Integer poll = tmpStack.pollFirst();
            this.push(poll.intValue());
        }
        return max;
    }
}
