package com.liyongquan.weeklycontest.bwc52;

public class MemLeak {
    /**
     * 模拟
     * <p>
     * 这还能通过？不会吧
     *
     * @param memory1
     * @param memory2
     * @return
     */
    public int[] memLeak(int memory1, int memory2) {
        return dfs(memory1, memory2, 1);
    }

    private int[] dfs(int m1, int m2, int c) {
        if (m1 < c && m2 < c) {
            return new int[]{c, m1, m2};
        }
        if (m1 >= m2) {
            return dfs(m1 - c, m2, c + 1);
        } else {
            return dfs(m1, m2 - c, c + 1);
        }
    }
}
