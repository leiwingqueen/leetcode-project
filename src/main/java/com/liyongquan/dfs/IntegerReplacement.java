package com.liyongquan.dfs;

import sun.nio.cs.ext.MacHebrew;

import java.util.HashMap;
import java.util.Map;

/**
 * 给定一个正整数 n，你可以做如下操作：
 * <p>
 * 1. 如果 n 是偶数，则用 n / 2替换 n。
 * 2. 如果 n 是奇数，则可以用 n + 1或n - 1替换 n。
 * n 变为 1 所需的最小替换次数是多少？
 * <p>
 * 示例 1:
 * <p>
 * 输入:
 * 8
 * <p>
 * 输出:
 * 3
 * <p>
 * 解释:
 * 8 -> 4 -> 2 -> 1
 * 示例 2:
 * <p>
 * 输入:
 * 7
 * <p>
 * 输出:
 * 4
 * <p>
 * 解释:
 * 7 -> 8 -> 4 -> 2 -> 1
 * 或
 * 7 -> 6 -> 3 -> 2 -> 1
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/integer-replacement
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class IntegerReplacement {
    private Map<Integer, Integer> cache = new HashMap<>();

    /**
     * 增加记忆
     * log(n)的时间效率
     *
     * @param n
     * @return
     */
    public int integerReplacement(int n) {
        if (n == 1) {
            return 0;
        }
        if (cache.containsKey(n)) {
            return cache.get(n);
        }
        if ((n & 1) == 0) {
            int r = integerReplacement(n >> 1) + 1;
            cache.put(n, r);
            return r;
        }
        int res = Math.min(integerReplacement(n / 2), integerReplacement(n / 2 + 1)) + 2;
        cache.put(n, res);
        return res;
    }

    /**
     * 带记忆的递归，
     * 同样超出最大栈的深度，无法通过
     *
     * @param n
     * @return
     */
    public int integerReplacement2(int n) {
        return dfs(n, new HashMap<>());
    }

    /**
     * 当最后一位为0，直接右移，count++;
     * 当最后一个为1，且上一位也为1，+1，然后count--(这样可以减少上一位的移动次数，直接一次移动把上一位也变成偶数了)
     * 否则 直接右移
     *
     * @param n
     * @return
     */
    public int integerReplacement3(int n) {
        int cnt = 0;
        while (n != 1) {
            if ((n & 1) == 0) {
                cnt++;
            } else if ((n & 2) == 0 && n != 3) {
                //3是一个特殊场景
                n++;
                cnt += 2;
            } else {
                n--;
                cnt += 2;
            }
            n >>= 1;
        }
        return cnt;
    }

    private int dfs(int n, Map<Integer, Integer> mem) {
        if (n == 1) {
            return 0;
        }
        if (mem.containsKey(n)) {
            return mem.get(n);
        }
        if ((n & 1) == 0) {
            int n2 = n >> 1;
            int c = dfs(n2, mem);
            mem.put(n2, c);
            return c + 1;
        }
        int c1 = dfs(n - 1, mem);
        mem.put(n - 1, c1);
        int c2 = dfs(n + 1, mem);
        mem.put(n + 1, c2);
        return Math.min(c1, c2) + 1;
    }

    /**
     * dp解法
     * <p>
     * 内存溢出
     *
     * @param n
     * @return
     */
    public int integerReplacement4(int n) {
        int[] dp = new int[n + 1];
        dp[1] = 0;
        for (int i = 2; i <= n; i++) {
            if (i % 2 == 0) {
                dp[i] = dp[i / 2] + 1;
            } else {
                //这里把+1 和 /2两个操作合并了，所以就能用dp
                dp[i] = Math.min(dp[(i + 1) / 2], dp[(i - 1) / 2]) + 2;
            }
        }
        return dp[n];
    }

    /**
     * 最低位消除
     * <p>
     * 0只需要一次操作
     * 1-需要2次操作
     * -1的操作，不影响整体的数字1的个数
     * +1的操作，可能会增加/减少整体数字1的个数
     * <p>
     * 直观感觉，1的个数会影响整体
     *
     * @param n
     * @return
     */
    public int integerReplacement5(int n) {
        //TODO
        return 0;
    }


}
