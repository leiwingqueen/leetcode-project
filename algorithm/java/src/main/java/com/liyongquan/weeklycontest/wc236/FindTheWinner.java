package com.liyongquan.weeklycontest.wc236;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class FindTheWinner {
    /**
     * 模拟
     *
     * @param n
     * @param k
     * @return
     */
    public int findTheWinner(int n, int k) {
        List<Integer> arr = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            arr.add(i + 1);
        }
        int idx = 0;
        while (arr.size() > 1) {
            //下一个位置
            idx = (idx + k - 1) % arr.size();
            //log.info("idx:{},value:{}", idx, arr.get(idx));
            arr.remove(idx);
            idx %= arr.size();
        }
        return arr.get(0);
    }
}
