package com.liyongquan.string;

import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author liyongquan
 * @date 2021/9/1
 */
@Slf4j
public class CompareVersionTest {
    private CompareVersion cv = new CompareVersion();

    @Test
    public void compareVersion() {
        int res = cv.compareVersion("0.1", "1.1");
        log.info("{}", res);
        Assert.assertEquals(-1, res);
    }
}