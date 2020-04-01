package com.liyongquan;

public class TwoSum {
    static class Solution {
        public int[] twoSum(int[] nums, int target) {
            for (int i = 0; i < nums.length-1; i++) {
                int a=nums[i];
                for (int j = i+1; j < nums.length; j++) {
                    int b=nums[j];
                    if (a+b==target) {
                        return new int[]{i,j};
                    }
                }
            }
            return new int[]{};
        }
    }

    public static void main(String[] args) {
        int[] a=new int[]{2, 7, 11, 15};
        Solution solution=new Solution();
        int[] ints = solution.twoSum(a, 9);
        System.out.println(ints[0]+" "+ints[1]);
    }
}
