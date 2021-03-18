package com.liyongquan.dp;

import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

@Slf4j
public class IsMatchTest {
    private IsMatch im = new IsMatch();

    /**
     * 测试用例:"aab"
     * "c*a*b"
     * 测试结果:false
     * 期望结果:true
     */
    @Test
    public void isMatch() {
        boolean res = im.isMatch("aab", "c*a*b");
        log.info("{}", res);
        Assert.assertEquals(true, res);
    }

    @Test
    public void test() {
        boolean res = im.isMatch("aaa", "a*a");
        log.info("{}", res);
        Assert.assertEquals(true, res);
    }

    /**
     * 测试用例:"bbbba"
     * ".*a*a"
     * 测试结果:false
     * 期望结果:true
     */
    @Test
    public void test2() {
        boolean res = im.isMatch("bbbba", ".*a*a");
        log.info("{}", res);
        Assert.assertEquals(true, res);
    }

    /**
     * 测试用例:"mississippi"
     * "mis*is*p*."
     * 测试结果:true
     * 期望结果:false
     */
    @Test
    public void test3() {
        boolean res = im.isMatch2("mississippi", "mis*is*p*.");
        log.info("{}", res);
        Assert.assertEquals(false, res);
    }
}