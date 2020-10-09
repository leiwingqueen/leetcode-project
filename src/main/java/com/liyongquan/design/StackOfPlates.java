package com.liyongquan.design;

import java.util.*;

/**
 * 堆盘子。设想有一堆盘子，堆太高可能会倒下来。因此，在现实生活中，盘子堆到一定高度时，我们就会另外堆一堆盘子。请实现数据结构SetOfStacks，模拟这种行为。SetOfStacks应该由多个栈组成，并且在前一个栈填满时新建一个栈。此外，SetOfStacks.push()和SetOfStacks.pop()应该与普通栈的操作方法相同（也就是说，pop()返回的值，应该跟只有一个栈时的情况一样）。 进阶：实现一个popAt(int index)方法，根据指定的子栈，执行pop操作。
 * <p>
 * 当某个栈为空时，应当删除该栈。当栈中没有元素或不存在该栈时，pop，popAt 应返回 -1.
 * <p>
 * 示例1:
 * <p>
 * 输入：
 * ["StackOfPlates", "push", "push", "popAt", "pop", "pop"]
 * [[1], [1], [2], [1], [], []]
 * 输出：
 * [null, null, null, 2, 1, -1]
 * 示例2:
 * <p>
 * 输入：
 * ["StackOfPlates", "push", "push", "push", "popAt", "popAt", "popAt"]
 * [[2], [1], [2], [3], [0], [0], [0]]
 * 输出：
 * [null, null, null, null, 2, 1, 3]
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/stack-of-plates-lcci
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class StackOfPlates {
    private int cap;
    private LinkedList<Deque<Integer>> stackList;

    public StackOfPlates(int cap) {
        this.cap = cap;
        stackList = new LinkedList<>();
        stackList.add(new LinkedList<>());
    }

    public void push(int val) {
        if (cap == 0) {
            return;
        }
        Deque<Integer> stack = stackList.peekLast();
        if (stack.size() == cap) {
            Deque<Integer> newStack = new LinkedList<>();
            newStack.offerLast(val);
            stackList.offerLast(newStack);
        } else {
            stack.offerLast(val);
        }
    }

    public int pop() {
        Deque<Integer> stack = stackList.peekLast();
        if (stack.isEmpty()) {
            return -1;
        }
        //空栈处理
        if (stack.size() == 1 && stackList.size() > 1) {
            Integer poll = stack.pollLast();
            stackList.pollLast();
            return poll;
        }
        return stack.pollLast();
    }

    public int popAt(int index) {
        if (index >= stackList.size()) {
            return -1;
        }
        //由于使用linklist,get操作的效率是O(n)
        Deque<Integer> stack = stackList.get(index);
        if (stack.isEmpty()) {
            return -1;
        }
        //空栈处理
        if (stack.size() == 1 && stackList.size() > 1) {
            Integer poll = stack.pollLast();
            stackList.remove(index);
            return poll;
        }
        return stack.pollLast();
    }
}
