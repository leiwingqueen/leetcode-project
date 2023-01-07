package com.liyongquan.weeklycontest.bwc95;

public class DataStream {
    private int value;
    private int k;
    private int cnt;

    public DataStream(int value, int k) {
        this.value = value;
        this.k = k;
        this.cnt = 0;
    }

    public boolean consec(int num) {
        if (num == value) {
            cnt++;
            if (cnt == k) {
                return true;
            }
        } else {
            cnt = 0;
        }
        return false;
    }
}
