package com.liyongquan.weeklycontest.wc307;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class LargestPalindromic {
    public String largestPalindromic(String num) {
        int[] cnt = new int[10];
        for (int i = 0; i < num.length(); i++) {
            int c = num.charAt(i) - '0';
            cnt[c]++;
        }
        Deque<Integer> deque = new LinkedList<>();
        for (int i = 9; i >= 0; i--) {
            if (cnt[i] > 0 && cnt[i] % 2 == 1) {
                deque.addLast(i);
                break;
            }
        }
        List<Integer> list = new ArrayList<>();
        for (int i = 9; i >= 0; i--) {
            if (i == 0 && list.size() == 0) {
                break;
            }
            while (cnt[i] >= 2) {
                list.add(i);
                cnt[i] -= 2;
            }
        }
        StringBuilder sb = new StringBuilder();
        for (Integer n : list) {
            sb.append((char) ('0' + n));
        }
        for (int i = 9; i >= 0; i--) {
            if (cnt[i] > 0) {
                sb.append((char) (i + '0'));
                break;
            }
        }
        for (int i = list.size() - 1; i >= 0; i--) {
            sb.append((char) ('0' + list.get(i)));
        }
        return sb.toString();
    }
}
