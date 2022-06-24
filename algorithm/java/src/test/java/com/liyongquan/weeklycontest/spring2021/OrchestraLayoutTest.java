package com.liyongquan.weeklycontest.spring2021;

import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

@Slf4j
public class OrchestraLayoutTest {
    private OrchestraLayout orchestraLayout = new OrchestraLayout();

    @Test
    public void orchestraLayout() {
        int res = orchestraLayout.orchestraLayout(449572, 209397, 306801);
        log.info("{}", res);
    }

    @Test
    public void test2() {
        int res = orchestraLayout.orchestraLayout2(877225311, 445777399, 843543753);
        log.info("{}", res);
        Assert.assertEquals(6, res);
    }
}