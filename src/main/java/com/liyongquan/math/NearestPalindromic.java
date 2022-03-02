package com.liyongquan.math;

public class NearestPalindromic {
    public String nearestPalindromic(String n) {
        int num = Integer.parseInt(n);
        int n1 = find(num);
        int n2 = find(num - 1);
        int n3 = find(num + 1);
        int res = Integer.MAX_VALUE;
        if (check(n1, num, res)) {
            res = n1;
        }
        if (check(n2, num, res)) {
            res = n2;
        }
        if (check(n3, num, res)) {
            res = n3;
        }
        return String.valueOf(res);
    }

    private boolean check(int n, int num, int res) {
        if (n != num && (Math.abs(n - num) < Math.abs(res - num) ||
                (n < num && Math.abs(n - num) <= Math.abs(res - num)))) {
            return true;
        } else {
            return false;
        }
    }

    private int find(int num) {
        char[] arr = String.valueOf(num).toCharArray();
        int l = 0, r = arr.length - 1;
        while (l < r) {
            arr[r--] = arr[l++];
        }
        return Integer.parseInt(String.valueOf(arr));
    }
}
