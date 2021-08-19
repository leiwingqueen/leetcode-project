package com.liyongquan.dp;

import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author liyongquan
 * @date 2021/8/19
 */
@Slf4j
public class Jump2Test {
    private Jump2 jump = new Jump2();

    @Test
    public void jump() {
        int res = this.jump.jump(new int[]{1, 2, 1, 1, 1});
        log.info("{}", res);
        Assert.assertEquals(3, res);
    }

}