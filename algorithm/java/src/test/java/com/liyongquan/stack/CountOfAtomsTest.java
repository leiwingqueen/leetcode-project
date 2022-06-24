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
        String res = ca.countOfAtoms2("H2O");
        log.info("{}", res);
        Assert.assertEquals("H2O", res);
    }

    /**
     * "Mg(OH)2"
     */
    @Test
    public void test2() {
        String[] args = {"Mg((H2O)2Na)4(F)(H2SO4)N"};
        String[] expects = {"FH18MgNNa4O12S"};
        int idx = 0;
        while (idx < args.length) {
            String actual = ca.countOfAtoms2(args[idx]);
            log.info("{}", actual);
            Assert.assertEquals(expects[idx], actual);
        }
    }
}