package com.liyongquan.backtrack;

import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author liyongquan
 * @date 2021/9/12
 */
@Slf4j
public class CheckValidStringTest {
    private CheckValidString cvs = new CheckValidString();

    @Test
    public void checkValidString() {
        boolean res = cvs.checkValidString2("()");
        Assert.assertEquals(true, res);
    }
}