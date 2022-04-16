package com.liyongquan.weeklycontest.lccpu2022;

public class GiveGem {
    public int giveGem(int[] gem, int[][] operations) {
        for (int[] op : operations) {
            int from = op[0];
            int to = op[1];
            int num = gem[from] / 2;
            gem[to] += num;
            gem[from] -= num;
        }
        int max = gem[0], min = gem[0];
        for (int g : gem) {
            max = Math.max(max, g);
            min = Math.min(min, g);
        }
        return max - min;
    }
}
