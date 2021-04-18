package com.liyongquan.weeklycontest.wc237;

import java.util.Arrays;

public class MaxIceCream {
    /**
     * 贪心
     * <p>
     * 排序后依次选择即可
     *
     * @param costs
     * @param coins
     * @return
     */
    public int maxIceCream(int[] costs, int coins) {
        Arrays.sort(costs);
        int idx = 0, sum = 0;
        int cnt = 0;
        while (idx < costs.length) {
            sum += costs[idx++];
            if (sum <= coins) {
                cnt++;
            } else {
                break;
            }
        }
        return cnt;
    }
}
