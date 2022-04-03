package com.liyongquan.weeklycontest.wc287;

public class ConvertTime {
    public int convertTime(String current, String correct) {
        int t1 = convert(current);
        int t2 = convert(correct);
        int cnt = 0;
        int diff = t2 - t1;
        int[] t = {60, 15, 5, 1};
        int idx = 0;
        while (diff > 0) {
            int c = diff / t[idx];
            cnt += c;
            diff -= c * t[idx];
            idx++;
        }
        return cnt;
    }

    public int convert(String s) {
        String[] split = s.split(":");
        return Integer.parseInt(split[0]) * 60 + Integer.parseInt(split[1]);
    }
}
