package com.liyongquan.util;

import java.util.Scanner;

public class TestAns {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.next();
        int pre = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                pre++;
            } else if (s.charAt(i) == ')') {
                pre--;
            }
            if (pre < 0) {
                System.out.println("No");
                return;
            }
        }
        if (pre == 0) {
            System.out.println("Yes");
        } else {
            System.out.println("No");
        }
    }
}
