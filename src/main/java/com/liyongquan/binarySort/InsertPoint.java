package com.liyongquan.binarySort;

public class InsertPoint {
    public int searchInsert(int[] nums, int target) {
        return searchInsert(nums,target,0,nums.length-1);
    }

    public int searchInsert(int[] nums, int target,int left,int right) {
        if(left==right){
            if (target<=nums[left]) {
                return left;
            }else {
                return left+1;
            }
        }
        int middle=left+(right-left+1)/2;
        int middleValue = nums[middle];
        if (middleValue ==target) {
            return middle;
        }
        if (middleValue>target) {
            if (middle-1<left) {
                return left;
            }
            return searchInsert(nums,target,left,middle-1);
        }else{
            if (middle+1>right) {
                return middle+1;
            }
            return searchInsert(nums,target,middle+1,right);
        }
    }



    public static void main(String[] args) {
        InsertPoint insertPoint=new InsertPoint();
        int i = insertPoint.searchInsert(new int[]{1, 3, 5, 6}, 7);
        System.out.println(i);
    }
}
