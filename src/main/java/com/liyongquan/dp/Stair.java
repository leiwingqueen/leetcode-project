package com.liyongquan.dp;

public class Stair {
    public int climbStairs(int n) {
        if (n==1) {
            return 1;
        }
        if (n==2) {
            return 2;
        }
        int dp0=1;
        int dp1=2;
        for (int i = 3; i <= n; i++) {
            int temp=dp1;
            dp1=dp0+dp1;
            dp0=temp;
        }
        return dp1;
    }

    public static void main(String[] args) {
        Stair stair=new Stair();
        System.out.println(stair.climbStairs(3));
    }
}
