package com.liyongquan.weeklycontest.wc240;

public class MaximumPopulation {
    /**
     * 数据量不大，直接暴力即可
     *
     * @param logs
     * @return
     */
    public int maximumPopulation(int[][] logs) {
        int min = Integer.MAX_VALUE, max = 0;
        for (int[] log : logs) {
            min = Math.min(min, log[0]);
            max = Math.max(max, log[1]);
        }
        int y = min;
        int maxCnt = 0;
        int maxYear = min;
        while (y < max) {
            int cnt = 0;
            for (int[] log : logs) {
                if (y >= log[0] && y < log[1]) {
                    cnt++;
                }
            }
            if (cnt > maxCnt) {
                maxCnt = cnt;
                maxYear = y;
            }
            y++;
        }
        return maxYear;
    }
}
