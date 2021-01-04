package com.liyongquan.dp;

public class Fibonacci {
    public int fib(int n) {
        if (n <= 1) {
            return n;
        }
        int fn0 = 0;
        int fn1 = 1;
        for (int i = 2; i <= n; i++) {
            int temp = fn1;
            fn1 = fn0 + fn1;
            fn0 = temp;
        }
        return fn1;
    }

    public static void main(String[] args) {
        Fibonacci fibonacci = new Fibonacci();
        int fib = fibonacci.fib(4);
        System.out.println(fib);
    }
}
