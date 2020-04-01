package com.liyongquan.dp;

public class MaxSubArray {
    public int maxSubArray(int[] nums) {
        if (nums.length==1) {
            return nums[0];
        }
        int dp0=0;
        int dp1=nums[0];
        for (int i = 1; i < nums.length; i++) {
            if (i==1) {
                dp0=nums[0];
            }else {
                dp0=max(dp0,dp1);
            }
            dp1=max(dp1+nums[i],nums[i]);
        }
        return max(dp0,dp1);
    }

    private int max(int a,int b){
        return a>b?a:b;
    }

    public static void main(String[] args) {
        MaxSubArray maxSubArray=new MaxSubArray();
        //int i = maxSubArray.maxSubArray(new int[]{-2, 1, -3, 4, -1, 2, 1, -5, 4});
        int i = maxSubArray.maxSubArray(new int[]{-1, -2});
        System.out.println(i);
    }
}
