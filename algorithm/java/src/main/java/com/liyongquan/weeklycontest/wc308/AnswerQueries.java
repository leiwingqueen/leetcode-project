package com.liyongquan.weeklycontest.wc308;

import java.util.Arrays;

public class AnswerQueries {
    public int[] answerQueries(int[] nums, int[] queries) {
        int n = nums.length;
        Arrays.sort(nums);
        int[] pre = new int[n + 1];
        for (int i = 0; i < n; i++) {
            pre[i + 1] = pre[i] + nums[i];
        }
        int m = queries.length;
        int[] res = new int[m];
        for (int i = 0; i < m; i++) {
            int j = 0;
            while (j <= n && pre[j] <= queries[i]) {
                j++;
            }
            res[i] = j - 1;
        }
        return res;
    }
}
