package com.liyongquan.math;

/**
 * https://leetcode-cn.com/problems/er-jin-zhi-zhong-1de-ge-shu-lcof/
 */
public class OneCount {
    // you need to treat n as an unsigned value
    public int hammingWeight(int n) {
        int count=0;
        while (n>0){
            if ((n&1)==1) {
                count++;
            }
            n=n>>1;
        }
        return count;
    }

    public static void main(String[] args) {
        OneCount oneCount=new OneCount();
        System.out.println(oneCount.hammingWeight(11));
    }
}
