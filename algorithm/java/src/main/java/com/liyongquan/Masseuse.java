package com.liyongquan;

import java.util.HashMap;
import java.util.Map;

/**
 * 递归+记忆
 */
public class Masseuse {
    static class Solution {
        public int massage(int[] nums) {
            Map<Integer,Integer> map =new HashMap<Integer, Integer>();
            return sum(nums,0,map);
        }

        public int sum(int[] nums,int index,Map<Integer,Integer> map){
            if (index>nums.length-1) {
                return 0;
            }
            if (map.containsKey(index)) {
                return map.get(index);
            }
            int i1 = sum(nums, index + 2,map) + nums[index];
            int i2 = sum(nums, index + 1,map);
            int result=i1>i2?i1:i2;
            map.put(index,result);
            return result;
        }
    }

    public static void main(String[] args) {
        int[] nums=new int[]{114,117,207,117,235,82,90,67,143,146,53,108,200,91,80,223,58,170,110,236,81,90,222,160,165,195,187,199,114,235,197,187,69,129,64,214,228,78,188,67,205,94,205,169,241,202,144,240};
        Solution solution=new Solution();
        int massage = solution.massage(nums);
        System.out.println(massage);
    }
}
