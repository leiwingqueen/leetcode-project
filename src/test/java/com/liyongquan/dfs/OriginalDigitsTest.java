package com.liyongquan.dfs;

import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author liyongquan
 * @date 2021/11/24
 */
@Slf4j
public class OriginalDigitsTest {

    @Test
    public void originalDigits() {
        OriginalDigits od = new OriginalDigits();
        String r = od.originalDigits("owoztneoer");
        Assert.assertEquals("012", r);
    }
}