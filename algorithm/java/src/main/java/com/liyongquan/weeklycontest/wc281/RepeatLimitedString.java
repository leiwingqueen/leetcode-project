package com.liyongquan.weeklycontest.wc281;

public class RepeatLimitedString {
    /**
     * 贪心
     * @param s
     * @param repeatLimit
     * @return
     */
    public String repeatLimitedString(String s, int repeatLimit) {
        int[] map = new int[26];
        for (int i = 0; i < s.length(); i++) {
            map[s.charAt(i) - 'a']++;
        }
        StringBuilder sb = new StringBuilder();
        int pre = -1;
        while (true) {
            //找到一个符合的字符
            int idx = 25;
            int best = -1;
            while (idx >= 0) {
                if (map[idx] > 0) {
                    if (best < 0) {
                        best = idx;
                        if (best != pre) {
                            idx = best;
                            break;
                        }
                    } else {
                        break;
                    }
                }
                idx--;
            }
            if (idx < 0) {
                return sb.toString();
            }
            pre = idx;
            int rep = 1;
            if (idx == best) {
                rep = Math.min(map[pre], repeatLimit);
            }
            for (int i = 0; i < rep; i++) {
                sb.append((char) ('a' + pre));
            }
            map[pre] -= rep;
        }
    }
}
