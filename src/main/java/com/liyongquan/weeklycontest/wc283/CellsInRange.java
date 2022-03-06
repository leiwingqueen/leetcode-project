package com.liyongquan.weeklycontest.wc283;

import java.util.ArrayList;
import java.util.List;

public class CellsInRange {
    public List<String> cellsInRange(String s) {
        String[] split = s.split(":");
        char c1 = split[0].charAt(0);
        char r1 = split[0].charAt(1);
        char c2 = split[1].charAt(0);
        char r2 = split[1].charAt(1);
        List<String> res = new ArrayList<>();
        for (char i = c1; i <= c2; i++) {
            for (char j = r1; j <= r2; j++) {
                res.add(String.valueOf(i) + j);
            }
        }
        return res;
    }
}
