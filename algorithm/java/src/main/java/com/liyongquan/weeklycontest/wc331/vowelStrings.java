package com.liyongquan.weeklycontest.wc331;

public class vowelStrings {
    public int[] vowelStrings(String[] words, int[][] queries) {
        char[] arr = {'a', 'e', 'i', 'o', 'u'};
        int n = words.length;
        int[] prefixSum = new int[n + 1];
        for (int i = 0; i < n; i++) {
            prefixSum[i + 1] = prefixSum[i];
            boolean flag = false;
            for (char c : arr) {
                if (c == words[i].charAt(0)) {
                    flag = true;
                    break;
                }
            }
            boolean flag2 = false;
            for (char c : arr) {
                if (c == words[i].charAt(words[i].length() - 1)) {
                    flag2 = true;
                    break;
                }
            }
            if (flag && flag2) {
                prefixSum[i + 1]++;
            }
        }
        int[] res = new int[queries.length];
        for (int i = 0; i < queries.length; i++) {
            int[] query = queries[i];
            res[i] = prefixSum[query[1] + 1] - prefixSum[query[0]];
        }
        return res;
    }
}
