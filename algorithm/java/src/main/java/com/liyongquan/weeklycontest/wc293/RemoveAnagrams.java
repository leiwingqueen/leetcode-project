package com.liyongquan.weeklycontest.wc293;

import java.util.*;

public class RemoveAnagrams {
    public List<String> removeAnagrams(String[] words) {
        List<String> res = new ArrayList<>();
        for (int i = 0; i < words.length; i++) {
            if (i == 0 || !equal(words[i], words[i - 1])) {
                res.add(words[i]);
            }
        }
        return res;
    }

    private boolean equal(String a, String b) {
        int[] arr1 = new int[26];
        for (int i = 0; i < a.length(); i++) {
            arr1[a.charAt(i) - 'a']++;
        }
        int[] arr2 = new int[26];
        for (int i = 0; i < b.length(); i++) {
            arr2[b.charAt(i) - 'a']++;
        }
        return Arrays.equals(arr1, arr2);
    }
}
