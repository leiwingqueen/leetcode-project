package com.liyongquan.math;

import com.liyongquan.linklist.ListNode;
import org.junit.Test;

import static org.junit.Assert.*;

public class PalindromeListTest {
    private PalindromeList palindromeList=new PalindromeList();
    @Test
    public void isPalindrome() {
        ListNode n1=new ListNode(-129);
        ListNode n2=new ListNode(-129);
        n1.next=n2;

        boolean palindrome = palindromeList.isPalindrome(n1);
        System.out.println(palindrome);
    }
}