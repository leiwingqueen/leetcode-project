package com.liyongquan.design;

//给你一个嵌套的整型列表。请你设计一个迭代器，使其能够遍历这个整型列表中的所有整数。
//
// 列表中的每一项或者为一个整数，或者是另一个列表。其中列表的元素也可能是整数或是其他列表。
//
//
//
// 示例 1:
//
// 输入: [[1,1],2,[1,1]]
//输出: [1,1,2,1,1]
//解释: 通过重复调用 next 直到 hasNext 返回 false，next 返回的元素的顺序应该是: [1,1,2,1,1]。
//
// 示例 2:
//
// 输入: [1,[4,[6]]]
//输出: [1,4,6]
//解释: 通过重复调用 next 直到 hasNext 返回 false，next 返回的元素的顺序应该是: [1,4,6]。
//
// Related Topics 栈 设计
// 👍 229 👎 0


//leetcode submit region begin(Prohibit modification and deletion)

import java.util.*;


// This is the interface that allows for creating nested lists.
// You should not implement it, or speculate about its implementation
interface NestedInteger {

    // @return true if this NestedInteger holds a single integer, rather than a nested list.
    public boolean isInteger();

    // @return the single integer that this NestedInteger holds, if it holds a single integer
    // Return null if this NestedInteger holds a nested list
    public Integer getInteger();

    // @return the nested list that this NestedInteger holds, if it holds a nested list
    // Return null if this NestedInteger holds a single integer
    public List<NestedInteger> getList();
}

public class NestedIterator implements Iterator<Integer> {
    //private Stack<Iterator<NestedInteger>> stack;
    private Deque<NestedInteger> deque;

    public NestedIterator(List<NestedInteger> nestedList) {
        deque = new LinkedList<>();
        for (NestedInteger ni : nestedList) {
            deque.offerLast(ni);
        }
    }

    @Override
    public Integer next() {
        while (!deque.isEmpty()) {
            NestedInteger ni = deque.pollFirst();
            if (ni.isInteger()) {
                return ni.getInteger();
            } else {
                //摊平继续放入
                List<NestedInteger> list = ni.getList();
                for (int i = list.size() - 1; i >= 0; i--) {
                    deque.offerFirst(list.get(i));
                }
            }
        }
        return null;
    }

    @Override
    public boolean hasNext() {
        while (!deque.isEmpty()) {
            NestedInteger ni = deque.peekFirst();
            if (ni.isInteger()) {
                return true;
            } else {
                deque.pollFirst();
                //摊平继续放入
                List<NestedInteger> list = ni.getList();
                for (int i = list.size() - 1; i >= 0; i--) {
                    deque.offerFirst(list.get(i));
                }
            }
        }
        return false;
    }
}
/**
 * Your NestedIterator object will be instantiated and called as such:
 * NestedIterator i = new NestedIterator(nestedList);
 * while (i.hasNext()) v[f()] = i.next();
 */
//leetcode submit region end(Prohibit modification and deletion)

