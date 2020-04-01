package com.liyongquan.math;

public class Teams {
    public int numTeams(int[] rating) {
        int l0,l1,r0,r1;
        int result=0;
        for (int i = 1; i < rating.length-1; i++) {
            l0=l1=r0=r1=0;
            for (int j = 0; j < i; j++) {
                if (rating[j]<rating[i]) {
                    l0++;
                }else {
                    l1++;
                }
            }
            for (int j = i+1; j < rating.length; j++) {
                if (rating[j]<rating[i]) {
                    r0++;
                }else {
                    r1++;
                }
            }
            int r = l0 * r1 + l1 * r0;
            result+= r;
        }
        return result;
    }

    public static void main(String[] args) {
        Teams teams=new Teams();
        int i = teams.numTeams(new int[]{1,2, 3, 4});
        System.out.println(i);
    }
}
