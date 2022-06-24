package com.liyongquan.dp;

/**
 * https://leetcode-cn.com/problems/qing-wa-tiao-tai-jie-wen-ti-lcof/
 * 斐波那契数列
 * 状态转移方程
 * f(n)=f(n-1)+f(n-2)
 * 终止条件
 * f(0)=0
 * f(1)=1
 */
public class Stair2 {
    public int climbStairs(int n) {
        if (n==0) {
            return 1;
        }
        if (n==1) {
            return 1;
        }
        int dp0=1;
        int dp1=1;
        for (int i = 2; i <= n; i++) {
            int temp=dp1;
            dp1=(dp0+dp1)%1000000007;
            dp0=temp;
        }
        return dp1;
    }

    public static void main(String[] args) {
        Stair2 stair2=new Stair2();
        int i = stair2.climbStairs(92);
        System.out.println(i);
    }
}
