package com.liyongquan.stack;

public class Kuohao {
    public int[] maxDepthAfterSplit(String seq) {
        int[] depthArray=new int[seq.length()];
        int depth=0;
        for (int i = 0; i < seq.length(); i++) {
            char c = seq.charAt(i);
            if (c=='(') {
                depth++;
                depthArray[i]=(depth+1)%2;
            }else if(c==')') {
                depthArray[i]=(depth+1)%2;
                depth--;
            }
        }
        return depthArray;
    }

    public static void main(String[] args) {
        Kuohao kuohao=new Kuohao();
        int[] ints = kuohao.maxDepthAfterSplit("(()())");
        for (int anInt : ints) {
            System.out.println(anInt);
        }
    }
}
