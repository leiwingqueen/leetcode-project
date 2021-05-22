package com.liyongquan.weeklycontest.wc241;


public class MinSwaps {
    public int minSwaps(String s) {
        int dif1 = diff(s, 1);
        int dif2 = diff(s, 0);
        if (dif1 < 0 && dif2 < 0) {
            return -1;
        } else if (dif1 < 0) {
            return dif2;
        } else if (dif2 < 0) {
            return dif1;
        } else {
            return Math.min(dif1, dif2);
        }
    }

    private int diff(String s, int start) {
        int oneDiff = 0, zeroDiff = 0;
        for (int i = 0; i < s.length(); i++) {
            int num = s.charAt(i) - '0';
            if (num != start) {
                if (start == 1) {
                    oneDiff++;
                } else {
                    zeroDiff++;
                }
            }
            start = (start + 1) % 2;
        }
        if (oneDiff != zeroDiff) {
            return -1;
        } else {
            return oneDiff;
        }
    }
}
