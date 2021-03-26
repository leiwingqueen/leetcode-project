package com.liyongquan.stack;

import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

@Slf4j
public class CountOfAtomsTest {
    private CountOfAtoms ca = new CountOfAtoms();

    @Test
    public void countOfAtoms() {
        String res = ca.countOfAtoms("H2O");
        log.info("{}", res);
        Assert.assertEquals("H2O", res);
    }
}