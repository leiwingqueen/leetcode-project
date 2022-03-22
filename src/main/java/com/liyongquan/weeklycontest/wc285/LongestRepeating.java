package com.liyongquan.weeklycontest.wc285;

public class LongestRepeating {
    public int[] longestRepeating(String s, String queryCharacters, int[] queryIndices) {
        int n = s.length();
        int k = queryCharacters.length();
        int[] res = new int[k];
        char[] arr = s.toCharArray();
        for (int i = 0; i < k; i++) {
            int idx = queryIndices[i];
            char ch = queryCharacters.charAt(i);
            arr[idx] = ch;
            int l = 0, r = 0;
            int max = 0;
            while (r < n) {
                while (r < n && arr[r] == arr[l]) {
                    r++;
                }
                max = Math.max(max, r - l);
                l = r;
            }
            res[i] = max;
        }
        return res;
    }

    //TODO:线段树
}
