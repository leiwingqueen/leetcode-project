package com.liyongquan.tesla;

// and B of N strings each, and an array C of N positive integers. TheyYou are given twcdescribe N monthly exrred from 0 to N-1) set up on an account The K-th expensei an amount Clkl at the end of each rtarting from month Alk] an(hardes the account withending in month Blk] (inclusive
//The format of each string is "MM-yyyy" (where mm denotes month, between 01 and 12, and yyyydenotes year, between 1900 and 2100), for example: "06-2021
//What is the minimueded for all of thes charaes to be paidWe cannot go into debt. In other words, the amount of money that remains in the account at the endof each month (after all the payments) should not drop below 0. The monthly income starts in theearliest month given in array A. The income is transfered onto the account at the beginning of themonth, i.e. income for a given month is always on the account before any of the charges are payedThe balance that remains in the account at the end of the month can be used in the following month
//Write a function:
//class Solution { public int solution(stringl1 A, Stringr1 B, intl c):
//that. diven arravs A and B consisting of N strinas each and an array C consisting of N positiventegers, representing the expenses as described above, returns the minimum monthly incomeneeded to cover all of the expenses
//Examples:
//1 GiveD  ="03-2021""04-2021""05-2021 B =03-2021""05-2021""05-2021" andC(20. 10, 151, the function should return 20. The total expenses are: 20 (in March), 10 (in April) and 2!(in May). Starting with income 20 (in March), the total balance at the end of each month will be: 0 (inMarch), 10 (in April) and 5 (in May).
//2. Given A ="10-2020""01-2020""02-2020""06-20217 B = 07-2021""03-2020" "10-2020""07-2021"7 and C = l 10 2. 901 the function should return 13
//3 Given A =01-1900""12-2099" "11-2099" 01-1901" B 12-1901" "12-2099" "12-2100""01-1902"] and C = [1, 1000, 998, 1], the function should return 7.
//Write an efficient algorithm for the following assumptions:
//N is an integer within the range [1..100,000], each element of array C is an integer within the range [1..1,0001.all dates in arrays A and B are correct datess in the format "mm-yyyy" between "01-
//1900"and"12-2100"Alk] is a date not later than Blk] for each k from 0 to N-1.

public class T2 {
    public int solution(String[] A, String[] B, int[] C) {
        int n = A.length;
        int[][] times = new int[n][2];
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            times[i][0] = convert(A[i]);
            times[i][1] = convert(B[i]);
            min = Math.min(min, times[i][0]);
            max = Math.max(max, times[i][1]);
        }
        int[] decrease = new int[max - min + 1];
        for (int i = 0; i < n; i++) {
            for (int j = times[i][0]; j <= times[i][1]; j++) {
                decrease[j - min] += C[i];
            }
        }
        int l = 0, r = 1000;
        while (l < r) {
            int mid = l + (r - l) / 2;
            if (check(decrease, mid)) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        return l;
    }

    private boolean check(int[] decrease, int income) {
        int cur = income;
        for (int i = 0; i < decrease.length; i++) {
            if (decrease[i] > cur) {
                return false;
            }
            cur += income - decrease[i];
        }
        return true;
    }

    private int convert(String ym) {
        int month = Integer.parseInt(ym.split("-")[0]);
        int year = Integer.parseInt(ym.split("-")[1]);
        return (year - 1900) * 12 + month;
    }

    public int solution2(String[] A, String[] B, int[] C) {
        int n = A.length;
        int[][] times = new int[n][2];
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            times[i][0] = convert(A[i]);
            times[i][1] = convert(B[i]);
            min = Math.min(min, times[i][0]);
            max = Math.max(max, times[i][1]);
        }
        // 差分数组
        int[] diff = new int[max - min + 2];
        for (int i = 0; i < n; i++) {
            int start = times[i][0] - min;
            int end = times[i][1] - min;
            diff[start] += C[i];
            diff[end + 1] -= C[i];
        }
        // 差分数组还原
        int maxDecrease = 0;
        int[] decrease = new int[max - min + 1];
        for (int i = 0; i < max - min + 1; i++) {
            if (i == 0) {
                decrease[i] = diff[i];
            } else {
                decrease[i] = decrease[i - 1] + diff[i];
            }
            maxDecrease = Math.max(maxDecrease, decrease[i]);
        }
        int l = 0, r = maxDecrease;
        while (l < r) {
            int mid = l + (r - l) / 2;
            if (check(decrease, mid)) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        return l;
    }
}
