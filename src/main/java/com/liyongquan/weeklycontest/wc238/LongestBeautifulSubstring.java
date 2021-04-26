package com.liyongquan.weeklycontest.wc238;

import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;

@Slf4j
public class LongestBeautifulSubstring {
    public int longestBeautifulSubstring(String word) {
        Map<Character, Integer> map = new HashMap<>();
        map.put('a', 0);
        map.put('e', 1);
        map.put('i', 2);
        map.put('o', 3);
        map.put('u', 4);
        int len = word.length();
        int l = 0;
        while (l < len && word.charAt(l) != 'a') {
            l++;
        }
        int r = l;
        int max = 0;
        char pre = 'a';
        while (r < len) {
            //log.info("l:{} r:{}", l, r);
            int distance = map.get(word.charAt(r)) - map.get(pre);
            pre = word.charAt(r);
            if (distance >= 0 && distance <= 1) {
                if (word.charAt(r) == 'u') {
                    //log.info("l:{},r:{},distance:{}", l, r, r - l);
                    max = Math.max(max, r - l + 1);
                }
            } else {
                l = r;
                //找到第一个a开头的字符
                while (l < len && word.charAt(l) != 'a') {
                    l++;
                }
                pre = 'a';
            }
            r++;
        }
        return max;
    }
}
