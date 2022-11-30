package com.liyongquan.design;

// 设计一个类似堆栈的数据结构，将元素推入堆栈，并从堆栈中弹出出现频率最高的元素。
//
//实现 FreqStack 类:
//
//FreqStack() 构造一个空的堆栈。
//void push(int val) 将一个整数 val 压入栈顶。
//int pop() 删除并返回堆栈中出现频率最高的元素。
//如果出现频率最高的元素不只一个，则移除并返回最接近栈顶的元素。
// 
//
//示例 1：
//
//输入：
//["FreqStack","push","push","push","push","push","push","pop","pop","pop","pop"],
//[[],[5],[7],[5],[7],[4],[5],[],[],[],[]]
//输出：[null,null,null,null,null,null,null,5,7,5,4]
//解释：
//FreqStack = new FreqStack();
//freqStack.push (5);//堆栈为 [5]
//freqStack.push (7);//堆栈是 [5,7]
//freqStack.push (5);//堆栈是 [5,7,5]
//freqStack.push (7);//堆栈是 [5,7,5,7]
//freqStack.push (4);//堆栈是 [5,7,5,7,4]
//freqStack.push (5);//堆栈是 [5,7,5,7,4,5]
//freqStack.pop ();//返回 5 ，因为 5 出现频率最高。堆栈变成 [5,7,5,7,4]。
//freqStack.pop ();//返回 7 ，因为 5 和 7 出现频率最高，但7最接近顶部。堆栈变成 [5,7,5,4]。
//freqStack.pop ();//返回 5 ，因为 5 出现频率最高。堆栈变成 [5,7,4]。
//freqStack.pop ();//返回 4 ，因为 4, 5 和 7 出现频率最高，但 4 是最接近顶部的。堆栈变成 [5,7]。
// 
//
//提示：
//
//0 <= val <= 109
//push 和 pop 的操作数不大于 2 * 104。
//输入保证在调用 pop 之前堆栈中至少有一个元素。
//
//来源：力扣（LeetCode）
//链接：https://leetcode.cn/problems/maximum-frequency-stack
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

import java.util.*;

// 这个写法其实是比较牵强的，低分飘过
public class FreqStack {
    private int ts;
    private Map<Integer, Stack<Integer>> stackMap;
    private TreeSet<Integer> set;

    public FreqStack() {
        ts = 0;
        stackMap = new HashMap<>();
        set = new TreeSet<>((o1, o2) -> {
            Stack<Integer> s1 = stackMap.get(o1);
            Stack<Integer> s2 = stackMap.get(o2);
            if (s1.size() != s2.size()) {
                return s2.size() - s1.size();
            } else {
                if (s2.size() == 0) {
                    return 0;
                } else {
                    return s2.peek() - s1.peek();
                }
            }
        });
    }

    public void push(int val) {
        int timestamp = ts++;
        if (stackMap.containsKey(val)) {
            // 重新排序
            set.remove(val);
        } else {
            stackMap.put(val, new Stack<>());
        }
        stackMap.get(val).push(timestamp);
        set.add(val);
    }

    public int pop() {
        Integer first = set.first();
        Stack<Integer> stack = stackMap.get(first);
        if (stack.size() == 0) {
            return -1;
        }
        set.remove(first);
        stack.pop();
        set.add(first);
        return first;
    }
}
