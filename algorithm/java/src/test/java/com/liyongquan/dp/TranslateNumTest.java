package com.liyongquan.dp;

import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author liyongquan
 * @date 2021/9/10
 */
@Slf4j
public class TranslateNumTest {
    private TranslateNum tn = new TranslateNum();

    @Test
    public void translateNum() {
        int res = tn.translateNum(25);
        log.info("{}", res);
        Assert.assertEquals(2, res);
    }
}