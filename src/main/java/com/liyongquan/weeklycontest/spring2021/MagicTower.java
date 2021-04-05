package com.liyongquan.weeklycontest.spring2021;

import java.util.LinkedList;
import java.util.PriorityQueue;

public class MagicTower {
    /**
     * 不通过
     *
     * @param nums
     * @return
     */
    public int magicTower(int[] nums) {
        int len = nums.length;
        int blood = 1;
        int last = 0, cnt = 0;
        for (int i = 0; i < len; i++) {
            if (nums[i] >= 0 || blood + nums[i] > 0) {
                blood += nums[i];
            } else {
                last += nums[i];
                cnt++;
            }
        }
        return blood + last <= 0 ? -1 : cnt;
    }

    /**
     * 求左边的最小值
     *
     * @param nums
     * @return
     */
    public int magicTower2(int[] nums) {
        int len = nums.length;
        long blood = 1;
        long last = 0;
        int cnt = 0;
        PriorityQueue<Long> pq = new PriorityQueue<>();
        for (int i = 0; i < len; i++) {
            pq.add((long)nums[i]);
            blood += nums[i];
            if (blood <= 0) {
                if (pq.isEmpty() || pq.peek() >= 0) {
                    return -1;
                }
                Long min = pq.poll();
                last += min;
                blood -= min;
                cnt++;
            }
        }
        return blood + last <= 0 ? -1 : cnt;
    }
}
