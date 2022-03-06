package com.liyongquan.stack;

import java.util.ArrayList;
import java.util.List;

public class GoodDaysToRobBank {
    /**
     * 暴力
     * <p>
     * 超时
     *
     * @param security
     * @param time
     * @return
     */
    public List<Integer> goodDaysToRobBank(int[] security, int time) {
        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < security.length; i++) {
            int l = i - 1;
            int r = i + 1;
            int cnt = time;
            while (cnt > 0 && l >= 0 && r < security.length) {
                if (security[l] < security[l + 1] || security[r] < security[r - 1]) {
                    break;
                }
                cnt--;
                l--;
                r++;
            }
            if (cnt == 0) {
                res.add(i);
            }
        }
        return res;
    }
}
