package com.liyongquan;

public class Masseuse2 {
    static class Solution {
        public int massage(int[] nums) {
            int dp0=0;
            int dp1=0;
            for (int i = 0; i < nums.length; i++) {
                int dpi0=max(dp0,dp1);
                dp1=dp0+nums[i];
                dp0=dpi0;
            }
            return max(dp0,dp1);
        }

        public int max(int a,int b){
            return a>b?a:b;
        }
    }

    public static void main(String[] args) {
        int[] nums=new int[]{114,117,207,117,235,82,90,67,143,146,53,108,200,91,80,223,58,170,110,236,81,90,222,160,165,195,187,199,114,235,197,187,69,129,64,214,228,78,188,67,205,94,205,169,241,202,144,240};
        //int[] nums=new int[]{1,2,3,1};
        Solution solution=new Solution();
        int massage = solution.massage(nums);
        System.out.println(massage);
    }
}
