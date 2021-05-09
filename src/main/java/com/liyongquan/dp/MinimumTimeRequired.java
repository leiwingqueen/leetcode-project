package com.liyongquan.dp;

import com.sun.tools.internal.xjc.reader.xmlschema.bindinfo.BIConversion;
import lombok.extern.slf4j.Slf4j;

import java.nio.file.attribute.UserDefinedFileAttributeView;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * 1723. 完成所有工作的最短时间
 * <p>
 * 给你一个整数数组 jobs ，其中 jobs[i] 是完成第 i 项工作要花费的时间。
 * <p>
 * 请你将这些工作分配给 k 位工人。所有工作都应该分配给工人，且每项工作只能分配给一位工人。工人的 工作时间 是完成分配给他们的所有工作花费时间的总和。请你设计一套最佳的工作分配方案，使工人的 最大工作时间 得以 最小化 。
 * <p>
 * 返回分配方案中尽可能 最小 的 最大工作时间 。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入：jobs = [3,2,3], k = 3
 * 输出：3
 * 解释：给每位工人分配一项工作，最大工作时间是 3 。
 * 示例 2：
 * <p>
 * 输入：jobs = [1,2,4,7,8], k = 2
 * 输出：11
 * 解释：按下述方式分配工作：
 * 1 号工人：1、2、8（工作时间 = 1 + 2 + 8 = 11）
 * 2 号工人：4、7（工作时间 = 4 + 7 = 11）
 * 最大工作时间是 11 。
 *  
 * <p>
 * 提示：
 * <p>
 * 1 <= k <= jobs.length <= 12
 * 1 <= jobs[i] <= 107
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/find-minimum-time-to-finish-all-jobs
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
@Slf4j
public class MinimumTimeRequired {
    /**
     * 老规矩，试试回溯
     * <p>
     * 时间复杂度O(k^n)
     * <p>
     * 必然超时
     *
     * @param jobs
     * @param k
     * @return
     */
    public int minimumTimeRequired(int[] jobs, int k) {
        return backtrace(jobs, new int[k], 0, jobs.length, k);
    }

    private int backtrace(int[] jobs, int[] cost, int idx, int len, int k) {
        if (idx == len) {
            int max = 0;
            for (int c : cost) {
                max = Math.max(max, c);
            }
            return max;
        }
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < k; i++) {
            cost[i] += jobs[idx];
            min = Math.min(min, backtrace(jobs, cost, idx + 1, len, k));
            cost[i] -= jobs[idx];
        }
        return min;
    }

    /**
     * 剪枝，去掉重复解
     * <p>
     * 只是把排列的问题转化成组合的问题，还是会超时
     *
     * @param jobs
     * @param k
     * @return
     */
    public int minimumTimeRequired2(int[] jobs, int k) {
        return backtrace2(jobs, new int[k], 0, jobs.length, k);
    }

    private int backtrace2(int[] jobs, int[] cost, int idx, int len, int k) {
        if (idx == len) {
            int max = 0;
            for (int c : cost) {
                max = Math.max(max, c);
            }
            return max;
        }
        int min = Integer.MAX_VALUE;
        Set<String> set = new HashSet<>();
        for (int i = 0; i < k; i++) {
            cost[i] += jobs[idx];
            String s = toStr(cost);
            if (set.contains(s)) {
                cost[i] -= jobs[idx];
                continue;
            }
            set.add(s);
            //log.info("s:{},idx:{}", s, idx);
            min = Math.min(min, backtrace2(jobs, cost, idx + 1, len, k));
            cost[i] -= jobs[idx];
        }
        return min;
    }

    private String toStr(int[] cost) {
        int[] arr = Arrays.copyOf(cost, cost.length);
        Arrays.sort(arr);
        StringBuilder sb = new StringBuilder();
        for (int c : arr) {
            sb.append(c + "#");
        }
        return sb.toString();
    }

    /**
     * dp解法
     *
     * @param jobs
     * @param k
     * @return
     */
    public int minimumTimeRequired3(int[] jobs, int k) {
        int len = jobs.length;
        int[][] dp = new int[k][len + 1];
        int sum = 0;
        for (int i = 1; i <= len; i++) {
            dp[0][i] = sum;
            sum += jobs[i - 1];
        }
        //后缀和
        int[] preSum = new int[len + 1];
        for (int i = len - 1; i >= 0; i--) {
            preSum[i] = preSum[i + 1] + jobs[i];
        }
        for (int i = 1; i < k; i++) {
            for (int j = 1; j <= len; j++) {
                dp[i][j] = Integer.MAX_VALUE;
                for (int l = j - 1; l >= 0; l--) {
                    int m = Math.max(dp[l][i - 1], preSum[l]);
                    dp[i][j] = Math.min(dp[i][j], m);
                }
            }
        }
        return dp[k - 1][len];
    }

    private int res = Integer.MAX_VALUE;

    public int minimumTimeRequired4(int[] jobs, int k) {
        backtrace4(jobs, new int[k], 0, k, 0, 0);
        return res;
    }

    private void backtrace4(int[] jobs, int[] cost, int idx, int k, int max, int used) {
        log.info("idx:{},used:{},max:{},res:{}", idx, used, max, res);
        //剪枝
        if (max >= res) {
            return;
        }
        if (idx == jobs.length) {
            log.info("res:{}", max);
            res = max;
            return;
        }
        //优先分配给空闲的工人
        if (used < k) {
            cost[used] = jobs[idx];
            backtrace4(jobs, cost, idx + 1, k, Math.max(max, jobs[idx]), used + 1);
            cost[used] = 0;
        }
        for (int i = 0; i < used; i++) {
            cost[i] += jobs[idx];
            backtrace4(jobs, cost, idx + 1, k, Math.max(max, cost[i]), used);
            cost[i] -= jobs[idx];
        }
    }
}
