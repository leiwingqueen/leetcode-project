package com.liyongquan.weeklycontest.bwc126;

import java.util.Comparator;
import java.util.PriorityQueue;

public class MinimizeStringValue {
    public String minimizeStringValue(String s) {
        int[] cnt = new int[26];
        int[] appendArr = new int[26];
        // 问号的数量
        int q = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '?') {
                q++;
            } else {
                cnt[s.charAt(i) - 'a']++;
            }
        }
        PriorityQueue<Integer> pq = new PriorityQueue<>((o1, o2) -> {
            if (cnt[o1] != cnt[o2]) {
                return cnt[o1] - cnt[o2];
            } else {
                return o1 - o2;
            }
        });
        for (int i = 0; i < 26; i++) {
            pq.offer(i);
        }
        while (q > 0) {
            Integer first = pq.poll();
            Integer second = pq.peek();
            int k = Math.min(cnt[second] - cnt[first], q);
            if (k == 0) {
                k++;
            }
            cnt[first] += k;
            appendArr[first] += k;
            pq.offer(first);
            q -= k;
        }
        // 开始填充
        StringBuilder sb = new StringBuilder();
        int p = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '?') {
                while (p < 26 && appendArr[p] == 0) {
                    p++;
                }
                sb.append((char) (p + 'a'));
                appendArr[p]--;
            } else {
                sb.append(s.charAt(i));
            }
        }
        return sb.toString();
    }
}
