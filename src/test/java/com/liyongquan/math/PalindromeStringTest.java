package com.liyongquan.math;

import org.junit.Test;

import static org.junit.Assert.*;

public class PalindromeStringTest {
    private PalindromeString palindromeString=new PalindromeString();
    @Test
    public void isPalindrome() {
        boolean palindrome = palindromeString.isPalindrome("A man, a plan, a canal: Panama");
        System.out.println(palindrome);
    }
}