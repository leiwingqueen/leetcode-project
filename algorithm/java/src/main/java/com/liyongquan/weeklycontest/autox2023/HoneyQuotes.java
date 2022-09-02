package com.liyongquan.weeklycontest.autox2023;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HoneyQuotes {
    public double[] honeyQuotes(int[][] handle) {
        Map<Integer, Integer> map = new HashMap<>();
        int cnt = 0;
        int sum = 0;
        List<Double> res = new ArrayList<>();
        for (int[] pair : handle) {
            if (pair[0] == 1) {
                map.put(pair[1], map.getOrDefault(pair[1], 0) + 1);
                sum += pair[1];
                cnt++;
            } else if (pair[0] == 2) {
                if (map.containsKey(pair[1]) && map.get(pair[1]) > 0) {
                    map.put(pair[1], map.get(pair[1]) - 1);
                    sum -= pair[1];
                    cnt--;
                }
            } else if (pair[0] == 3) {
                if (cnt == 0) {
                    res.add(-1D);
                } else {
                    res.add(sum / (double) cnt);
                }
            } else {
                if (cnt == 0) {
                    res.add(-1D);
                } else {
                    double r = 0D;
                    double avg = sum / (double) cnt;
                    for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
                        Integer value = entry.getKey();
                        Integer num = entry.getValue();
                        r += Math.pow(value - avg, 2) * num / cnt;
                    }
                    res.add(r);
                }
            }
        }
        double[] ans = new double[res.size()];
        int idx = 0;
        for (Double re : res) {
            ans[idx++] = re;
        }
        return ans;
    }
}
