package com.liyongquan.array;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class Card {
    public boolean hasGroupsSizeX(int[] deck) {
        Map<Integer,Integer> map=new TreeMap<>();
        for (int i : deck) {
            int count=map.containsKey(i)?map.get(i)+1:1;
            map.put(i,count);
        }
        int gcd=-1;
        for (Integer value : map.values()) {
            if(gcd<0){
                gcd=value;
                continue;
            }
            gcd = gcd(gcd,value);
            if(gcd<2){
                return false;
            }
        }
        if(gcd<2){
            return false;
        }
        return true;
    }

    private int gcd(int a,int b){
        if(a<b){
            int temp=a;
            a=b;
            b=temp;
        }
        if (b==0) {
            return a;
        }
        return gcd(b,a%b);
    }

    public static void main(String[] args) {
        int[] a={1,1,1,1,1,0,0,0};
        //int[] a={1,1,2,2,2,2};
        Card card=new Card();
        System.out.println(card.hasGroupsSizeX(a));
    }
}
