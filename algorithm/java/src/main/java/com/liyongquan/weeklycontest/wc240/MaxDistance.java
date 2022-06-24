package com.liyongquan.weeklycontest.wc240;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MaxDistance {
    /**
     * 滑动窗口
     *
     * @param nums1
     * @param nums2
     * @return
     */
    public int maxDistance(int[] nums1, int[] nums2) {
        int len1 = nums1.length, len2 = nums2.length;
        int l = 0, r = 0;
        int max = 0;
        while (l < len1 && r < len2) {
            while (r < len2 && nums2[r] >= nums1[l]) {
                r++;
            }
            //log.info("r:{},l:{}", r, l);
            max = Math.max(r - l - 1, max);
            l++;
            if (r < l) {
                r = l;
            }
        }
        return max;
    }
}
