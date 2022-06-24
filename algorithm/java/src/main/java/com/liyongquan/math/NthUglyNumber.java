package com.liyongquan.math;

import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Set;

/**
 * 编写一个程序，找出第 n 个丑数。
 * <p>
 * 丑数就是质因数只包含 2, 3, 5 的正整数。
 * <p>
 * 示例:
 * <p>
 * 输入: n = 10
 * 输出: 12
 * 解释: 1, 2, 3, 4, 5, 6, 8, 9, 10, 12 是前 10 个丑数。
 * 说明:  
 * <p>
 * 1 是丑数。
 * n 不超过1690。
 * <p>
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/ugly-number-ii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class NthUglyNumber {
    /**
     * 时间复杂度O(nlog(n))
     * <p>
     * 超时
     *
     * @param n
     * @return
     */
    public int nthUglyNumber(int n) {
        int[] arr = {2, 3, 5};
        int cnt = 0;
        int i = 1;
        while (cnt < n) {
            int num = i;
            for (int j : arr) {
                while (num % j == 0) {
                    num /= j;
                }
            }
            if (num == 1) {
                cnt++;
            }
            i++;
        }
        return i - 1;
    }

    /**
     * 1
     * 2,3,5
     * 2->4,6,10
     * 3->9,15
     * 5->25
     * <p>
     * 小顶堆
     *
     * @param n
     * @return
     */
    public int nthUglyNumber2(int n) {
        //小顶堆排序，并且实现bfs
        PriorityQueue<Long> pq = new PriorityQueue<>();
        //set去重
        Set<Long> set = new HashSet<>();
        pq.add(1L);
        set.add(1L);
        int[] arr = {2, 3, 5};
        int cnt = 0;
        long last = 0;
        while (cnt < n) {
            last = pq.poll();
            //生成下一个元素
            for (int num : arr) {
                long next = last * num;
                if (!set.contains(next)) {
                    pq.add(next);
                    set.add(next);
                }
            }
            cnt++;
        }
        return (int) last;
    }
}
