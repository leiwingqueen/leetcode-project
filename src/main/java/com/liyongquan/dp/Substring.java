package com.liyongquan.dp;

public class Substring {
    public String longestPalindrome(String s) {
        String result="";
        int[][] subset=new int[s.length()][s.length()];
        for(int i=0;i<s.length();i++){
            for(int j=i;j<s.length();j++){
                if (isPalindrome(s,subset,i,j)&&(j-i+1)>result.length()) {
                    result=s.substring(i,j+1);
                }
            }
        }
        return result;
    }

    private boolean isPalindrome(String s,int[][] subset,int i,int j){
        if(i>=j){
            return true;
        }
        if (s.charAt(i)!=s.charAt(j)) {
            return false;
        }
        if (subset[i][j]!=0) {
            return subset[i][j]==1;
        }

        boolean palindrome = isPalindrome(s, subset, i + 1, j - 1);
        subset[i][j]=palindrome?1:2;
        return palindrome;
    }

    public static void main(String[] args) {
        Substring s=new Substring();
        String p = s.longestPalindrome("a");
        System.out.println(p);
    }
}
