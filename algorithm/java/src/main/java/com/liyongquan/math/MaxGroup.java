package com.liyongquan.math;

public class MaxGroup {
    public int countLargestGroup(int n) {
        int length=0;
        int temp=n;
        while (temp>0){
            temp/=10;
            length++;
        }
        int max=0;
        int[] result=new int[length*9+1];
        for (int i = 1; i <= n; i++) {
            int sum = sum(i);
            result[sum]+=1;
            if (result[sum]>max) {
                max=result[sum];
            }
        }
        int count=0;
        for (int i = 0; i < result.length; i++) {
            if (max==result[i]) {
                count++;
            }
        }
        return count;
    }

    public int sum(int n){
        int result=0;
        while (n>0){
            result+=n%10;
            n/=10;
        }
        return result;
    }

    public static void main(String[] args) {
        MaxGroup group=new MaxGroup();
        int i = group.countLargestGroup(24);
        System.out.println(i);
    }
}
