package com.liyongquan.math;

import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

@Slf4j
public class ConvertToTitleTest {
    private ConvertToTitle ctt = new ConvertToTitle();

    @Test
    public void convertToTitle() {
        String s = ctt.convertToTitle(701);
        log.info("{}", s);
        Assert.assertEquals("ZY", s);
    }

    @Test
    public void convertToTitle2() {
        String s = ctt.convertToTitle(1);
        log.info("{}", s);
        Assert.assertEquals("A", s);
    }

    @Test
    public void convertToTitle3() {
        String s = ctt.convertToTitle(27);
        log.info("{}", s);
        Assert.assertEquals("AA", s);
    }
}