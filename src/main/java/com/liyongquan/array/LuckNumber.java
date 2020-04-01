package com.liyongquan.array;

public class LuckNumber {
    public int findLucky(int[] arr) {
        int[] c=new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            int a = arr[i];
            if (a>arr.length) {
                continue;
            }
            c[a-1]++;
        }
        int result=-1;
        for (int i = 0; i < c.length; i++) {
            if (c[i]==i+1) {
                result=i+1;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        LuckNumber luckNumber=new LuckNumber();
        int lucky = luckNumber.findLucky(new int[]{2, 2, 2, 3,3});
        System.out.println(lucky);
    }
}
