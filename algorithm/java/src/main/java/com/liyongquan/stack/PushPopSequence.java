package com.liyongquan.stack;

import java.util.Stack;

/**
 * 输入两个整数序列，第一个序列表示栈的压入顺序，请判断第二个序列是否为该栈的弹出顺序。
 * 假设压入栈的所有数字均不相等。例如，序列 {1,2,3,4,5} 是某栈的压栈序列，
 * 序列 {4,5,3,2,1} 是该压栈序列popped对应的一个弹出序列，但 {4,3,5,1,2} 就不可能是该压栈
 * 序列的弹出序列。
 *
 *
 *
 * 示例 1：
 *
 * 输入：pushed = [1,2,3,4,5], popped = [4,5,3,2,1]
 * 输出：true
 * 解释：我们可以按以下顺序执行：
 * push(1), push(2), push(3), push(4), pop() -> 4,
 * push(5), pop() -> 5, pop() -> 3, pop() -> 2, pop() -> 1
 *
 * 示例 2：
 *
 * 输入：pushed = [1,2,3,4,5], popped = [4,3,5,1,2]
 * 输出：false
 * 解释：1 不能在 2 之前弹出。
 *
 *
 *
 * 提示：
 *
 *     0 <= pushed.length == popped.length <= 1000
 *     0 <= pushed[i], popped[i] < 1000
 *     pushed 是 popped 的排列。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/zhan-de-ya-ru-dan-chu-xu-lie-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class PushPopSequence {
    public boolean validateStackSequences(int[] pushed, int[] popped) {
        Stack<Integer> stack=new Stack<>();
        int length=pushed.length;
        int i1=0;
        int i2=0;
        while (i2<length){
            //只能pop的场景
            if (i1>=length) {
                if (stack.peek().intValue()!=popped[i2]) {
                    return false;
                }
                i2++;
                stack.pop();
                continue;
            }
            //优先尝试pop，不能再使用push
            if(!stack.isEmpty()&&stack.peek().intValue()==popped[i2]){
                i2++;
                stack.pop();
            }else {
                stack.push(pushed[i1]);
                i1++;
            }
        }
        return true;
    }

    public static void main(String[] args) {

    }
}
