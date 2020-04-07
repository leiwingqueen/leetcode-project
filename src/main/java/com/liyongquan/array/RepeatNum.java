package com.liyongquan.array;

public class RepeatNum {
    public int findRepeatNumber(int[] nums) {
        int[] bitmap=new int[nums.length];
        for (int num : nums) {
            bitmap[num]++;
            if (bitmap[num]>1) {
                return num;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        RepeatNum repeatNum=new RepeatNum();
        int[] nums=new int[]{2, 3, 1, 0, 2, 5, 3};
        int repeatNumber = repeatNum.findRepeatNumber(nums);
        System.out.println(repeatNumber);
    }
}
