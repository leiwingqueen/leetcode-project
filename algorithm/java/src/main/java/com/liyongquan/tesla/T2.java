package com.liyongquan.tesla;

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
