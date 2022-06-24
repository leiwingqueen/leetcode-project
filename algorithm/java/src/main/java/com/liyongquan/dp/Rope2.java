package com.liyongquan.dp;

import java.math.BigInteger;

public class Rope2 {
    /**
     * 假设F(n,m)为n长度的分成m段的乘积
     * 则f(n)=max{F(n,m)}。其中1<m<n
     *
     * dp方程:F(n,m)=max{F(n-1,m-1)*1,F(n-2,m-1)*2,...F(1,m-1)*(n-1)}
     *
     * 边界值:
     * if n<m : F(n,m)=0
     * if n==m : F(n,m)=1
     * if m=1 :F(n,m)=n
     *
     * 由上面的特性，我们对dp方程还可以做进一步的简化:
     * F(n,m)=max{F(n-1,m-1)*1,F(n-2,m-1)*2,...F(m-1,m-1)*(n-m+1)}
     *
     * 假如我们把中间结果构造成一个n*m的二维数组，则数组中轴线右上方的结点都是没必要计算的。
     *
     * 复杂度约为O(n(n-1)/2*(n/2))。比O(n^3)稍好点
     *
     * @param n
     * @return
     */
    public int cuttingRope(int n) {
        //为了简化下标的理解，只使用1~n
        if (n<=2) {
            return 1;
        }
        BigInteger[] middleResult=new BigInteger[n+1];
        middleResult[1]=new BigInteger("1");
        middleResult[2]=new BigInteger("1");
        for (int i = 3; i <= n; i++) {
            BigInteger max=new BigInteger("0");
            for (int j = i-1; j >=1; j--) {
                //仅分两段的场景
                BigInteger r = new BigInteger(String.valueOf(j * (i - j)));
                BigInteger multiply = middleResult[j].multiply(new BigInteger(String.valueOf(i-j)));
                if (multiply.compareTo(max)>0) {
                    max=multiply;
                }
                if (r.compareTo(max)>0) {
                    max=r;
                }
            }
            middleResult[i]=max;
        }
        print(middleResult);
        return middleResult[n].mod(new BigInteger("1000000007")).intValue();
    }

    public static void main(String[] args) {
        Rope2 rope=new Rope2();
        long i = rope.cuttingRope(10);
        System.out.println(i);
    }

    private void print(BigInteger[] array) {
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i] + ",");
        }
        System.out.println();
    }
}
