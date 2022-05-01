package com.liyongquan.weeklycontest.wc291;

import java.util.HashMap;
import java.util.Map;

public class MinimumCardPickup {
    public int minimumCardPickup(int[] cards) {
        int n = cards.length;
        int res = -1;
        int l = 0, r = 0;
        Map<Integer, Integer> cnt = new HashMap<>();
        while (r < n) {
            while (r < n && !cnt.containsKey(cards[r])) {
                cnt.put(cards[r++], 1);
            }
            if (r == n) {
                return res;
            }
            cnt.put(cards[r], 2);
            //左窗口移动
            while (l < r && cards[l] != cards[r]) {
                cnt.remove(cards[l]);
                l++;
            }
            if (res < 0 || r - l + 1 < res) {
                res = r - l + 1;
            }
            cnt.put(cards[l], cnt.get(cards[l]) - 1);
            l++;
            r++;
        }
        return res;
    }
}
