package com.liyongquan.heap;

//313. 超级丑数
//超级丑数 是一个正整数，并满足其所有质因数都出现在质数数组 primes 中。
//
//给你一个整数 n 和一个整数数组 primes ，返回第 n 个 超级丑数 。
//
//题目数据保证第 n 个 超级丑数 在 32-bit 带符号整数范围内。
//
// 
//
//示例 1：
//
//输入：n = 12, primes = [2,7,13,19]
//输出：32
//解释：给定长度为 4 的质数数组 primes = [2,7,13,19]，前 12 个超级丑数序列为：[1,2,4,7,8,13,14,16,19,26,28,32] 。
//示例 2：
//
//输入：n = 1, primes = [2,3,5]
//输出：1
//解释：1 不含质因数，因此它的所有质因数都在质数数组 primes = [2,3,5] 中。
// 
//提示：
//
//1 <= n <= 106
//1 <= primes.length <= 100
//2 <= primes[i] <= 1000
//题目数据 保证 primes[i] 是一个质数
//primes 中的所有值都 互不相同 ，且按 递增顺序 排列
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/super-ugly-number
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

/**
 * @author liyongquan
 * @date 2021/8/9
 */
public class NthSuperUglyNumber {
    /**
     * bfs，使用优先级队列解决排序的问题
     *
     * @param n
     * @param primes
     * @return
     */
    public int nthSuperUglyNumber(int n, int[] primes) {
        int cnt = 0;
        int res = 1;
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        pq.add(1);
        Set<Integer> visit = new HashSet<>();
        visit.add(1);
        while (cnt < n) {
            Integer num = pq.poll();
            res = num;
            for (int prime : primes) {
                //解决溢出的场景
                long next = (long) num * prime;
                if (next > Integer.MAX_VALUE) {
                    continue;
                }
                int nextInt = (int) next;
                if (!visit.contains(nextInt)) {
                    pq.add(nextInt);
                    visit.add(nextInt);
                }
            }
            cnt++;
        }
        return res;
    }
}
