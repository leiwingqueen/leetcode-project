package com.liyongquan.weeklycontest.wc285;

public class CountHillValley {
    public int countHillValley(int[] nums) {
        int len = nums.length;
        int cnt = 0;
        int i = 1;
        while (i < len - 1) {
            int l = i - 1;
            while (l >= 0 && nums[l] == nums[i]) {
                l--;
            }
            if (l < 0) {
                i++;
                continue;
            }
            int r = i + 1;
            while (r < len && nums[r] == nums[i]) {
                r++;
            }
            if (r == len) {
                return cnt;
            }
            if ((nums[l] < nums[i] && nums[r] < nums[i]) || (nums[l] > nums[i] && nums[r] > nums[i])) {
                cnt++;
            }
            i = r;
        }
        return cnt;
    }
}
