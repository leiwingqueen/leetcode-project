package com.liyongquan.weeklycontest.wc281;

public class CountEven {
    public int countEven(int num) {
        int cnt = 0;
        for (int i = 1; i <= num; i++) {
            if (check(i)) {
                cnt++;
            }
        }
        return cnt;
    }

    private boolean check(int n) {
        int cnt = 0;
        while (n > 0) {
            cnt += n % 10;
            n /= 10;
        }
        return cnt % 2 == 0;
    }
}
