package com.liyongquan;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class UniqueLcci {
    static class Solution {
        public boolean isUnique(String astr) {
            int[] bitMap=new int[256];
            for(int i=0;i<astr.length();i++){
                int c = astr.charAt(i);
                if (bitMap[c]==1) {
                    return false;
                }
                bitMap[c]=1;
            }
            return true;
        }
    }

    public static void main(String[] args) {

    }
}
