package com.liyongquan.weeklycontest.wc306;

import java.util.TreeSet;

// 这贪心的写法确实让我有点难受
public class SmallestNumber {
    public String smallestNumber(String pattern) {
        int n = pattern.length();
        int[] res = new int[n + 1];
        res[0] = 1;
        TreeSet<Integer> set = new TreeSet<>();
        for (int i = 2; i <= 9; i++) {
            set.add(i);
        }
        for (int i = 0; i < n; i++) {
            int pre = res[i];
            if (pattern.charAt(i) == 'I') {
                Integer ceiling = set.ceiling(pre);
                if (ceiling != null) {
                    res[i + 1] = ceiling;
                    set.remove(ceiling);
                } else {
                    //交换
                    Integer floor = set.floor(pre);
                    res[i + 1] = res[i];
                    res[i] = floor;
                    set.remove(floor);
                    //看下是否需要继续交换
                    for (int j = i - 1; j >= 0; j--) {
                        if (pattern.charAt(j) == 'I' && res[j + 1] < res[j] ||
                                pattern.charAt(j) == 'D' && res[j + 1] > res[j]) {
                            int tmp = res[j + 1];
                            res[j + 1] = res[j];
                            res[j] = tmp;
                        }
                    }
                }
            } else {
                Integer first = set.first();
                if (first != null && first < pre) {
                    res[i + 1] = first;
                    set.remove(first);
                } else {
                    //交换
                    Integer ceiling = set.ceiling(pre);
                    res[i + 1] = res[i];
                    res[i] = ceiling;
                    set.remove(ceiling);
                    //看下是否需要继续交换
                    for (int j = i - 1; j >= 0; j--) {
                        if (pattern.charAt(j) == 'I' && res[j + 1] < res[j] ||
                                pattern.charAt(j) == 'D' && res[j + 1] > res[j]) {
                            int tmp = res[j + 1];
                            res[j + 1] = res[j];
                            res[j] = tmp;
                        }
                    }
                }
            }
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i <= n; i++) {
            sb.append(res[i]);
        }
        return sb.toString();
    }
}
