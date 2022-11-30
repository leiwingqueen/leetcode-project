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

// 看了题解后的优化解法
public class FreqStack2 {
    private Map<Integer, Integer> freq;
    private TreeMap<Integer, Stack<Integer>> stackMap;
    private int maxFreq;

    public FreqStack2() {
        stackMap = new TreeMap<>();
        freq = new HashMap<>();
        maxFreq = 0;
    }

    public void push(int val) {
        freq.put(val, freq.getOrDefault(val, 0) + 1);
        Integer cnt = freq.get(val);
        maxFreq = Math.max(maxFreq, cnt);
        if (!stackMap.containsKey(cnt)) {
            stackMap.put(cnt, new Stack<>());
        }
        stackMap.get(cnt).push(val);
    }

    public int pop() {
        Stack<Integer> stack = stackMap.get(maxFreq);
        Integer val = stack.pop();
        freq.put(val, freq.get(val) - 1);
        if (stack.size() == 0) {
            stackMap.remove(maxFreq);
            maxFreq = stackMap.size() > 0 ? stackMap.lastKey() : 0;
        }
        return val;
    }
}
