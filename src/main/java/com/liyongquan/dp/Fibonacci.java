package com.liyongquan.dp;

public class Fibonacci {
    public int fib(int N) {
        if (N==0) {
            return 0;
        }
        if (N==1) {
            return 1;
        }
        int fn0=0;
        int fn1=1;
        int temp=0;
        for (int i = 2; i <= N; i++) {
            temp=fn1;
            fn1=fn0+fn1;
            fn0=temp;
        }
        return fn1;
    }

    public static void main(String[] args) {
        Fibonacci fibonacci=new Fibonacci();
        int fib = fibonacci.fib(4);
        System.out.println(fib);
    }
}
