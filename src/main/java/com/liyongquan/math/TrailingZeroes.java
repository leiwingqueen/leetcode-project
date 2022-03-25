package com.liyongquan.math;

//172. 阶乘后的零
//给定一个整数 n ，返回 n! 结果中尾随零的数量。
//
//提示 n! = n * (n - 1) * (n - 2) * ... * 3 * 2 * 1
//
// 
//
//示例 1：
//
//输入：n = 3
//输出：0
//解释：3! = 6 ，不含尾随 0
//示例 2：
//
//输入：n = 5
//输出：1
//解释：5! = 120 ，有一个尾随 0
//示例 3：
//
//输入：n = 0
//输出：0
// 
//
//提示：
//
//0 <= n <= 104
// 
//
//进阶：你可以设计并实现对数时间复杂度的算法来解决此问题吗？
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/factorial-trailing-zeroes
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public class TrailingZeroes {
    /**
     * 核心是计算数字里面包含的2和5的因子有多少对
     *
     * @param n
     * @return
     */
    public int trailingZeroes(int n) {
        int[] cnt = new int[2];
        for (int i = 2; i <= n; i++) {
            int[] check = check(i);
            cnt[0] += check[0];
            cnt[1] += check[1];
        }
        return Math.min(cnt[0], cnt[1]);
    }

    private int[] check(int num) {
        int[] res = new int[2];
        while (num > 0 && num % 2 == 0) {
            res[0]++;
            num /= 2;
        }
        while (num > 0 && num % 5 == 0) {
            res[1]++;
            num /= 5;
        }
        return res;
    }

    /**
     * 上面基础上改进，BFS?
     *
     * @param n
     * @return
     */
    public int trailingZeroes2(int n) {
        int[] cnt = {0, 0};
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{1, 0, 0});
        while (!queue.isEmpty()) {
            int size = queue.size();
            Set<Integer> set = new HashSet<>();
            for (int i = 0; i < size; i++) {
                int[] poll = queue.poll();
                cnt[0] += poll[1];
                cnt[1] += poll[2];
                int next1 = poll[0] * 2;
                if (next1 <= n && !set.contains(next1)) {
                    set.add(next1);
                    queue.add(new int[]{next1, poll[1] + 1, poll[2]});
                }
                int next2 = poll[0] * 5;
                if (next2 <= n && !set.contains(next2)) {
                    set.add(next2);
                    queue.add(new int[]{next2, poll[1], poll[2] + 1});
                }
            }
        }
        return Math.min(cnt[0], cnt[1]);
    }
}
