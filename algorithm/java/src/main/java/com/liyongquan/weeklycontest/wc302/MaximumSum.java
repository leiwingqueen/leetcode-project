package com.liyongquan.weeklycontest.wc302;

import java.util.ArrayList;
import java.util.List;

public class MaximumSum {
    public int maximumSum(int[] nums) {
        List<Integer> list = new ArrayList<>(nums.length);
        for (int num : nums) {
            list.add(num);
        }
        list.sort((o1, o2) -> {
            int c1 = cal(o1);
            int c2 = cal(o2);
            if (c1 != c2) {
                return c1 - c2;
            }
            return o1 - o2;
        });
        int p1 = 0, p2 = 0;
        int res = -1;
        while (p2 < nums.length) {
            if (cal(list.get(p2)) == cal(list.get(p1))) {
                p2++;
            } else {
                if (p2 - p1 > 1) {
                    res = Math.max(res, list.get(p2 - 1) + list.get(p2 - 2));
                }
                p1 = p2;
            }
        }
        if (p2 - p1 > 1) {
            res = Math.max(res, list.get(p2 - 1) + list.get(p2 - 2));
        }
        return res;
    }

    public int cal(int num) {
        int res = 0;
        while (num > 0) {
            res += num % 10;
            num /= 10;
        }
        return res;
    }
}
