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
        int r = l + 1;
        int max = 0;
        while (r < len) {
            r++;
            //log.info("l:{} r:{}", l, r);
            int distance = map.get(word.charAt(r - 1)) - map.get(word.charAt(r - 2));
            if (distance >= 0 && distance <= 1) {
                if (word.charAt(r - 1) == 'u') {
                    //log.info("l:{},r:{},distance:{}", l, r, r - l);
                    max = Math.max(max, r - l);
                }
            } else {
                l = r;
                //找到第一个a开头的字符
                while (l < len && word.charAt(l) != 'a') {
                    l++;
                }
                r = l + 1;
                //log.info("move l:{}", l);
            }
        }
        return max;
    }
}
