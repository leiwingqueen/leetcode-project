package com.liyongquan.tree;

import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

@Slf4j
public class VerifyPreorderSerializationTest {
    private VerifyPreorderSerialization vp = new VerifyPreorderSerialization();

    /**
     * "9,3,4,#,#,1,#,#,2,#,6,#,#"
     */
    @Test
    public void isValidSerialization() {
        boolean res = vp.isValidSerialization("9,3,4,#,#,1,#,#,2,#,6,#,#");
        log.info("{}", res);
        Assert.assertEquals(true, res);
    }

    /**
     * "1,#,#,#,#"
     */
    @Test
    public void test() {
        boolean res = vp.isValidSerialization("1,#,#,#,#");
        log.info("{}", res);
        Assert.assertEquals(false, res);
    }
}