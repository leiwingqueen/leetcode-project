package com.liyongquan.weeklycontest.wc293;

public class LargestCombination {
    /**
     * 最舒服就这一题了
     * @param candidates
     * @return
     */
    public int largestCombination(int[] candidates) {
        int bit = 0b1;
        int res = 0;
        for (int i = 0; i < 31; i++) {
            int cnt = 0;
            for (int candidate : candidates) {
                if ((candidate & bit) != 0) {
                    cnt++;
                }
            }
            res = Math.max(res, cnt);
            bit <<= 1;
        }
        return res;
    }
}
