package com.liyongquan.string;

import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

@Slf4j
public class RemovePalindromeSubTest {
    private RemovePalindromeSub sub = new RemovePalindromeSub();

    @Test
    public void removePalindromeSub() {
        int res = sub.removePalindromeSub2("babababbababab");
        Assert.assertEquals(1, res);
    }
}