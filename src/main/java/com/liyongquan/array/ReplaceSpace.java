package com.liyongquan.array;

public class ReplaceSpace {
    public String replaceSpace(String s) {
        StringBuilder builder=new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c==' ') {
                builder.append("%20");
            }else{
                builder.append(c);
            }
        }
        return builder.toString();
    }

    public static void main(String[] args) {
        ReplaceSpace replaceSpace=new ReplaceSpace();
        String s = replaceSpace.replaceSpace("We are happy.");
        System.out.println(s);
    }
}
