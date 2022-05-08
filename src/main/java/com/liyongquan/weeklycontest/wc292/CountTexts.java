package com.liyongquan.weeklycontest.wc292;

import javax.sound.midi.Soundbank;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class CountTexts {
    public static final Map<Integer, Integer> mp = new HashMap<>();

    static {
        mp.put(2, 3);
        mp.put(3, 3);
        mp.put(4, 3);
        mp.put(5, 3);
        mp.put(6, 3);
        mp.put(7, 4);
        mp.put(8, 3);
        mp.put(9, 4);
    }

    public static final int MOD = 1_000_000_007;

    public int countTexts(String pressedKeys) {
        int len = pressedKeys.length();
        int n = Math.max(len, 4);
        int[] dp3 = new int[n];
        dp3[0] = 1;
        dp3[1] = 2;
        dp3[2] = 4;
        for (int i = 3; i < n; i++) {
            dp3[i] = (dp3[i] + dp3[i - 1]) % MOD;
            dp3[i] = (dp3[i] + dp3[i - 2]) % MOD;
            dp3[i] = (dp3[i] + dp3[i - 3]) % MOD;
            //dp3[i] = (dp3[i - 1] + dp3[i - 2] + dp3[i - 3]) % MOD;
        }
        int[] dp4 = new int[n];
        dp4[0] = 1;
        dp4[1] = 2;
        dp4[2] = 4;
        dp4[3] = 8;
        for (int i = 4; i < n; i++) {
            dp4[i] = (dp4[i] + dp4[i - 1]) % MOD;
            dp4[i] = (dp4[i] + dp4[i - 2]) % MOD;
            dp4[i] = (dp4[i] + dp4[i - 3]) % MOD;
            dp4[i] = (dp4[i] + dp4[i - 4]) % MOD;
            //dp4[i] = (int) ((long) dp4[i - 1] + dp4[i - 2] + dp4[i - 3] + dp4[i - 4]) % MOD;
        }
        int l = 0, r = 0;
        BigDecimal res = BigDecimal.ONE;
        while (r < len) {
            char ch = pressedKeys.charAt(l);
            if (pressedKeys.charAt(r) == ch) {
                r++;
            } else {
                if (mp.get(ch - '0') == 3) {
                    res = res.multiply(BigDecimal.valueOf(dp3[r - l - 1])).remainder(BigDecimal.valueOf(MOD));
                    //System.out.println("*" + dp3[r - l - 1]);
                } else {
                    res = res.multiply(BigDecimal.valueOf(dp4[r - l - 1])).remainder(BigDecimal.valueOf(MOD));
                    //res = (int) ((long) res * dp4[r - l - 1] % MOD);
                    //System.out.println("*" + dp4[r - l - 1]);
                }
                //System.out.println("res:" + res.intValue());

                l = r;
            }
        }
        if (r > l) {
            char ch = pressedKeys.charAt(l);
            if (mp.get(ch - '0') == 3) {
                res = res.multiply(BigDecimal.valueOf(dp3[r - l - 1])).remainder(BigDecimal.valueOf(MOD));
                //System.out.println("*" + dp3[r - l - 1]);
            } else {
                res = res.multiply(BigDecimal.valueOf(dp4[r - l - 1])).remainder(BigDecimal.valueOf(MOD));
                //System.out.println("*" + dp4[r - l - 1]);
            }
        }
        return res.intValue();
    }
}
