package com.liyongquan.dp;

public class Stair {
    /**
     * dp表达式
     * f(n)=f(n-1)+f(n-2)
     * 初始化：
     * f(1)=1
     * f(2)=2
     *
     * @param n
     * @return
     */
    public int climbStairs(int n) {
        if (n <= 2) {
            return n;
        }
        int dp0 = 1, dp1 = 2;
        for (int i = 3; i <= n; i++) {
            int temp = dp1;
            dp1 = dp0 + dp1;
            dp0 = temp;
        }
        return dp1;
    }

    public static void main(String[] args) {
        Stair stair = new Stair();
        System.out.println(stair.climbStairs(3));
    }
}
