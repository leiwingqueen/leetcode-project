package com.liyongquan.greedy;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class SubsequenceTest {
    private Subsequence subsequence = new Subsequence();

    @Test
    public void isSubsequence() {
        boolean subsequence = this.subsequence.isSubsequence("abc", "ahbgdc");
        Assert.assertEquals(true, subsequence);
    }
}