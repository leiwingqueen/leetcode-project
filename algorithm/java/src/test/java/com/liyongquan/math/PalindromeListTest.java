package com.liyongquan.math;

import com.liyongquan.linklist.ListNode;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class PalindromeListTest {
    private PalindromeList palindromeList = new PalindromeList();

    @Test
    public void isPalindrome() {
        ListNode n1 = new ListNode(1);
        ListNode n2 = new ListNode(1);
        ListNode n3 = new ListNode(2);
        ListNode n4 = new ListNode(1);
        n1.next = n2;
        n2.next = n3;
        n3.next = n4;

        boolean palindrome = palindromeList.isPalindrome2(n1);
        Assert.assertTrue(!palindrome);
    }
}