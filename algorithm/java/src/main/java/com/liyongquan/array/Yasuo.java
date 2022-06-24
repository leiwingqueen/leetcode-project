package com.liyongquan.array;

public class Yasuo {
    public int minimumLengthEncoding(String[] words) {
        int[] w=new int[words.length];
        for (int i = 0; i < words.length; i++) {
            if (w[i]==1) {
                continue;
            }
            for (int j = i+1; j < words.length; j++) {
                if (w[j]==1) {
                    continue;
                }
                if (subString(words[i],words[j])) {
                    if (words[i].length() < words[j].length()) {
                        w[i]=1;
                        break;
                    }
                    w[j]=1;
                }
            }
        }
        int result=0;
        for (int i = 0; i < w.length; i++) {
            if (w[i]==0) {
                result+=words[i].length()+1;
            }
        }
        return result;
    }

    private boolean subString(String a,String b){
        for (int i = 0; i < a.length()&&i<b.length(); i++) {
            if (a.charAt(a.length()-1-i)!=b.charAt(b.length()-1-i)) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        Yasuo yasuo=new Yasuo();
        String[] a=new String[]{"time", "me", "bell"};
        int result = yasuo.minimumLengthEncoding(a);
        System.out.println(result);
    }
}
