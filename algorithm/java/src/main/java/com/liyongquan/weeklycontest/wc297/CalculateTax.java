package com.liyongquan.weeklycontest.wc297;

public class CalculateTax {
    public double calculateTax(int[][] brackets, int income) {
        double res = 0D;
        int pre = 0;
        for (int[] bracket : brackets) {
            int d = Math.min(bracket[0] - pre, income);
            res += bracket[1] * d / 100D;
            income -= bracket[0] - pre;
            if (income < 0) {
                break;
            }
            pre = bracket[0];
        }
        return res;
    }
}
