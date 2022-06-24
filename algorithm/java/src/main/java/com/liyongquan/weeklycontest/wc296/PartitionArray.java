package com.liyongquan.weeklycontest.wc296;

import java.util.Arrays;

public class PartitionArray {
    public int partitionArray(int[] nums, int k) {
        Arrays.sort(nums);
        int cnt = 0;
        int l = 0, r = 0;
        while (r < nums.length) {
            if (nums[r] - nums[l] <= k) {
                r++;
            } else {
                cnt++;
                l = r;
            }
        }
        if (r > l) {
            cnt++;
        }
        return cnt;
    }
}
