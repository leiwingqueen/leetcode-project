package com.liyongquan.math;

import com.liyongquan.square.Shape;

public class Reverse {
    public int reverse(int x) {
        int positive=x>=0?1:0;
        int[] a=new int[10];
        int index=0;
        x=x>=0?x:-x;
        while (x>0){
            int mode=x%10;
            x=x/10;
            a[index]=mode;
            index++;
        }
        long out=0;
        for (int i = 0; i < index; i++) {
            out=out*10;
            out+=a[i];
        }
        if (out>Integer.MAX_VALUE) {
            out=0;
        }
        return positive==1?(int)out:-(int)out;
    }

    public static void main(String[] args) {
        Reverse reverse=new Reverse();
        System.out.println(reverse.reverse(-120));
    }
}
