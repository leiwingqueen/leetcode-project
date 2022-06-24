package com.liyongquan.slidewindow;

import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author liyongquan
 * @date 2021/8/4
 */
@Slf4j
public class TriangleNumberTest {
    private TriangleNumber tn = new TriangleNumber();

    @Test
    public void triangleNumber() {
        int res = tn.triangleNumber(new int[]{2, 2, 3, 4});
        log.info("{}", res);
        Assert.assertEquals(3, res);
    }

    @Test
    public void test2() {
        int res = tn.triangleNumber(new int[]{4, 2, 3, 4});
        log.info("{}", res);
        Assert.assertEquals(4, res);
    }
}