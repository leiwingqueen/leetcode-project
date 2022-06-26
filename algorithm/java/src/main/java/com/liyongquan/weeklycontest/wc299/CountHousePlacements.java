package com.liyongquan.weeklycontest.wc299;

import java.math.BigDecimal;

public class CountHousePlacements {
    public int countHousePlacements(int n) {
        int mod = 1_000_000_007;
        int f0 = 1;
        int f1 = 1;
        for (int i = 1; i < n; i++) {
            int nf0 = (f0 + f1) % mod;
            int nf1 = f0;
            f0 = nf0;
            f1 = nf1;
        }
        int m = (f0 + f1) % mod;
        return BigDecimal.valueOf(m).multiply(BigDecimal.valueOf(m)).remainder(BigDecimal.valueOf(mod)).intValue();
    }
}
