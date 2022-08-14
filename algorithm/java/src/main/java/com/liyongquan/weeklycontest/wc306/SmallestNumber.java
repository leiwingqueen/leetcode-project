package com.liyongquan.weeklycontest.wc306;

import com.sun.corba.se.spi.ior.TaggedProfileTemplate;

import java.util.*;

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

    //https://leetcode.cn/problems/construct-smallest-number-from-di-string/solution/by-wangzhizhi-n03o/
    // 拓补排序 这个解法可以
    public String smallestNumber2(String pattern) {
        int n = pattern.length();
        int[] res = new int[n + 1];
        List<Integer>[] graph = new List[n + 1];
        for (int i = 0; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }
        int[] degree = new int[n + 1];
        for (int i = 0; i < pattern.length(); i++) {
            char ch = pattern.charAt(i);
            if (ch == 'I') {
                graph[i].add(i + 1);
                degree[i + 1]++;
            } else {
                graph[i + 1].add(i);
                degree[i]++;
            }
        }
        TreeSet<Integer> set = new TreeSet<>();
        for (int i = 0; i <= n; i++) {
            set.add(i);
        }
        int num = 1;
        for (int i = 0; i <= n; i++) {
            // find degree=0
            int select = 0;
            for (Integer idx : set) {
                if (degree[idx] == 0) {
                    select = idx;
                    break;
                }
            }
            res[select] = num++;
            // update degree
            set.remove(select);
            for (Integer to : graph[select]) {
                degree[to]--;
            }
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i <= n; i++) {
            sb.append(res[i]);
        }
        return sb.toString();
    }
}
