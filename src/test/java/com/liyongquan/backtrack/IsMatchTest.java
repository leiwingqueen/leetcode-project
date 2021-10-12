package com.liyongquan.backtrack;

import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author liyongquan
 * @date 2021/10/12
 */
@Slf4j
public class IsMatchTest {
    IsMatch match = new IsMatch();

    @Test
    public void isMatch2() {
        boolean res = match.isMatch2("aa", "a*");
        Assert.assertEquals(true, res);
    }
}