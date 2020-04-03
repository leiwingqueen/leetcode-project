package com.liyongquan.array;

public class StringToInt {
    public int myAtoi(String str) {
        int i=0;
        long result=0;
        int positive=1;
        char first=' ';
        for (; i < str.length(); i++) {
            first= str.charAt(i);
            if (first!=' ') {
                break;
            }
        }
        if (first=='-'||first=='+'){
            positive=first=='-'?0:1;
        }else if(first>='0' && first<='9'){
            result+=first-'0';
        }else {
            return 0;
        }
        for (int j = i+1; j < str.length(); j++) {
            char c = str.charAt(j);
            if(c>='0'&&c<='9'){
                if (positive==1) {
                    result=result*10+(c-'0');
                }else{
                    result=result*10-(c-'0');
                }
                if (result>Integer.MAX_VALUE) {
                    result=Integer.MAX_VALUE;
                    break;
                }
                if(result<Integer.MIN_VALUE){
                    result=Integer.MIN_VALUE;
                    break;
                }
            }else {
                break;
            }
        }
        return (int) result;
    }


    public static void main(String[] args) {
        StringToInt stringToInt=new StringToInt();
        int i = stringToInt.myAtoi("+1");
        System.out.println(i);
    }
}
